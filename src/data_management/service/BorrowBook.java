package data_management.service;

import ADT.ListADT;
import data_management.entity.*;

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

        if (!bookToBorrow.isBorrowed() && borrowingStudent.canBorrow()) {
            bookToBorrow.setBorrowed(true);
            bookToBorrow.setPersonInBorrowed(borrowingStudent);
            bookToBorrow.setBorrowedDate();

            borrowingStudent.addBorrowedBooks(bookToBorrow);
            return true;
        }
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

        if (book.getPenaltyFee() < 0 && !isBroken) {
            throw new IllegalArgumentException("Book does not have a penalty fee for the student.");
        }


        double change = calChange(amount, calPenaltyFee(bookID, isBroken));

        if (change < 0) {
            return -1;
        }

        books.get(0).setIsPenaltyPayed(true);
        return change;
    }

    private double calChange(double amountPayed, double PenaltyFee) {
        if (amountPayed < PenaltyFee) {
            System.out.println("Amount paid is less than the penalty fee.");
            return -1;
        }
        return amountPayed - PenaltyFee;
    }

    public double calPenaltyFee(int bookID, boolean isBroken) {
        BookDataService bookDataService = new BookDataService();
        ListADT<Book> books = bookDataService.search(book -> book.getId() == bookID);
        
        if (books.len() != 1) {
            throw new IllegalArgumentException("Book with the specified ID does not exist.");
        }

        return books.get(0).getPenaltyFee() + (isBroken ? books.get(0).getPrice() : 0);
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

            return true;
        }
        return false;
    }
}
