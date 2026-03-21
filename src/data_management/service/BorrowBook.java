package data_management.service;

import ADT.ListADT;
import data_management.entity.*;
import java.time.LocalDate;
import util.Testing;

public class BorrowBook {
    public boolean borrowBook(int bookID, int studentID) {
        BookDataService bookDataService = new BookDataService();
        UserDataService userDataService = new UserDataService();

        ListADT<Book> books = bookDataService.search(book -> book.getId() == bookID);
        ListADT<UserInfo> students = userDataService.search(student -> student.getId() == studentID);

        if (books.len() != 1) {
            throw new IllegalArgumentException("Book with the specified ID does not exist.");
        }
        if (students.len() != 1) {
            throw new IllegalArgumentException("User with the specified ID does not exist.");
        } else if (!(students.get(0) instanceof Student)) {
            throw new IllegalArgumentException("User with the specified ID is not a student.");
        }

        Book bookToBorrow = books.get(0);
        Student borrowingStudent = (Student) students.get(0);

        Boolean canBorrow = borrowingStudent.canBorrow();
        if (!canBorrow){
            return false;
        }

        if (!bookToBorrow.isBorrowed()) {
            bookToBorrow.setBorrowed(true);
            bookToBorrow.setPersonInBorrowed(borrowingStudent);
            bookToBorrow.setBorrowedDate();

            borrowingStudent.addBorrowedBooks(bookToBorrow);

            HistoryRecorder.recordBookAction(bookID, studentID, BookHistory.TransactionType.BORROW, LocalDate.now());
            return true;
        }

        System.out.println("The book borrowed already.");
        return false;
    }

    public double payingPenalty(int bookID, int studentID, double amount, boolean isBroken) {
        BookDataService bookDataService = new BookDataService();
        ListADT<Book> books = bookDataService.search(book -> book.getId() == bookID);
        
        if (books.len() != 1) {
            throw new IllegalArgumentException("Book with the specified ID does not exist.");
        }
        
        ListADT<Book> borrowedBooks = bookDataService.search(
            book -> book.getPersonInBorrowed() != null &&
                    book.getPersonInBorrowed().getId() == studentID &&
                    book.getId() == bookID
        );

        if (borrowedBooks.len() != 1) {
            throw new IllegalArgumentException("Student with the specified ID did not borrow this book.");
        }

        Book book = borrowedBooks.get(0);
        Boolean isNewBroken = book.isBorken() || isBroken;

        if (book.getPenaltyFee() < 0 && !isNewBroken) {
            throw new IllegalArgumentException("Book does not have a penalty fee for the student.");
        }

        ListADT<Double> penaltyFees = calPenaltyFee(bookID, isNewBroken);
        double lateOfReturnPenalty = penaltyFees.get(0);
        double brokenBookPenalty = penaltyFees.get(1);

        double change = calChange(amount, lateOfReturnPenalty + brokenBookPenalty);

        if (change < 0) {
            return -1;
        }

        // Payment successful, mark the penalty as paid
        HistoryRecorder.recordPenalty("Late Return", lateOfReturnPenalty, LocalDate.now());
        if (isNewBroken) {
            book.setBroken(isBroken);
            System.out.println(book.isBorken());
            HistoryRecorder.recordPenalty("Broken Book", brokenBookPenalty, LocalDate.now());
        }

        
        book.setIsPenaltyPayed(true);
        return change;
    }

    private double calChange(double amountPayed, double PenaltyFee) {
        if (amountPayed < PenaltyFee) {
            System.out.println("Amount paid is less than the penalty fee.");
            return -1;
        }
        return amountPayed - PenaltyFee;
    }

    public ListADT<Double> calPenaltyFee(int bookID, boolean isBroken) {
        BookDataService bookDataService = new BookDataService();
        ListADT<Book> books = bookDataService.search(book -> book.getId() == bookID);
        
        if (books.len() != 1) {
            throw new IllegalArgumentException("Book with the specified ID does not exist.");
        }

        // Checking the borken is case by current student or privious student
        Book book = books.get(0);
        Boolean isNewBroken = book.isBorken() || isBroken;


        ListADT<Double> penaltyFees = new ListADT<>();
        penaltyFees.add(book.getPenaltyFee());
        penaltyFees.add(isNewBroken ? book.getPrice() : 0);

        return penaltyFees;
    }

    public boolean returnBook(int bookID, int studentID, boolean isBroken) {
        BookDataService bookDataService = new BookDataService();
        UserDataService userDataService = new UserDataService();

        ListADT<Book> books = bookDataService.search(book -> book.getId() == bookID);
        ListADT<UserInfo> students = userDataService.search(student -> student.getId() == studentID);

        if (books.len() != 1) {
            throw new IllegalArgumentException("Book with the specified ID does not exist.");
        }
        if (students.len() != 1) {
            throw new IllegalArgumentException("User with the specified ID does not exist.");
        } else if (!(students.get(0) instanceof Student)) {
            throw new IllegalArgumentException("User with the specified ID is not a student.");
        }

        Book bookToReturn = books.get(0);
        Student returningStudent = (Student) students.get(0);

        if (bookToReturn.isBorrowed() && bookToReturn.getPersonInBorrowed().getId() == returningStudent.getId()) {
            if (bookToReturn.getPenaltyFee() > 0) {
                if (!bookToReturn.isPenaltyPayed()) {
                    System.out.println("Please pay the penalty fee of RM" + calPenaltyFee(bookID, isBroken) + " before returning the book.");
                    return false;
                }
            }

            bookToReturn.setBorrowed(false);
            bookToReturn.resetBorrowedDate();
            bookToReturn.setPersonInBorrowed(null);
            bookToReturn.setIsPenaltyPayed(false);

            int indexToRemove = returningStudent.getBorrowedBooks().findAll(book -> book.getId() == bookID).get(0);
            returningStudent.getBorrowedBooks().remove(indexToRemove);

            HistoryRecorder.recordBookAction(bookID, studentID, BookHistory.TransactionType.RETURN, LocalDate.now());

            return true;
        }
        return false;
    }

    public static void main(String arg[]){
        UserDataService userDataService = new UserDataService();
        Testing.addTestUsers(userDataService);

        BookDataService bookDataService = new BookDataService();
        Testing.addTestBooks(bookDataService);

        BorrowBook borrowBook = new BorrowBook();
        boolean success = borrowBook.borrowBook(1, 1) && borrowBook.borrowBook(2, 1);
        System.out.println("Borrowing " + (success ? "successful" : "failed"));

        LocalDate date = LocalDate.of(2024, 2, 6);
        bookDataService.search(b -> b.getId() == 1).get(0).setBorrowedDate(date);

        // black listed testing.
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("Is black listed checking.");
        success = borrowBook.borrowBook(3, 1);
        System.out.println("Borrowing " + (success ? "successful" : "failed"));

        // Is broken testing
        System.out.println("");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("Is broken checking.");
        borrowBook.payingPenalty(1, 1, 1000, true);
        borrowBook.returnBook(1, 1, true);

        success = borrowBook.borrowBook(1, 1);
        System.out.println("Borrowing " + (success ? "successful" : "failed"));

        ListADT<Double> panaltyFees = borrowBook.calPenaltyFee(1, true);
        System.out.println("Panalty that have to pay: " + (panaltyFees.get(0) + panaltyFees.get(1)));

    }
}
