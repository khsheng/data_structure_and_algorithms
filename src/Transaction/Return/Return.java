package Transaction.Return;

import ADT.ListADT;
import data_management.entity.Book;
import data_management.entity.Student;
import data_management.entity.UserInfo;
import data_management.service.BookDataService;
import data_management.service.BorrowBook;
import data_management.service.UserDataService;
import data_management.service.BookListPagination;
import java.util.Scanner;

public class Return {
    private BookDataService bookDataService;
    private UserDataService userDataService;
    private BorrowBook borrowBookService;
    private Scanner scanner;

    public Return() {
        this(new Scanner(System.in));
    }

    public Return(Scanner scanner) {
        this.scanner = scanner;
        this.bookDataService = new BookDataService();
        this.userDataService = new UserDataService();
        this.borrowBookService = new BorrowBook();
    }

    /**
     * Interactive return process - shows borrowed books first
     * Asks for student ID first, then displays borrowed books, then asks for book ID
     * @return A ReturnResult object containing success status and detailed message
     */
    public ReturnResult returnBookInteractive() {
        // Step 1: Get Student ID
        System.out.print("Enter Student ID: ");
        int studentID = getIntInput();

        // Step 2: Verify student exists
        ListADT<UserInfo> users = userDataService.search(user -> user.getId() == studentID);
        if (users.len() != 1) {
            return new ReturnResult(false, "Error: User with ID " + studentID + " does not exist.");
        }

        // Step 3: Verify user is a student
        if (!(users.get(0) instanceof Student)) {
            return new ReturnResult(false, "Error: User with ID " + studentID + " is not a student.");
        }
        Student student = (Student) users.get(0);

        // Step 4: Get borrowed books for this student
        ListADT<Book> borrowedBooks = student.getBorrowedBooks();
        if (borrowedBooks.len() == 0) {
            return new ReturnResult(false, "Error: Student " + student.getName() + " has no borrowed books.");
        }

        // Step 5: Display borrowed books
        System.out.println("\n--- Books Borrowed by " + student.getName() + " ---");
        displayBorrowedBooks(borrowedBooks);

        // Step 6: Get Book ID to return
        System.out.print("\nEnter Book ID to return: ");
        int bookID = getIntInput();

        // Step 7: Call the main returnBook method
        return returnBook(bookID, studentID);
    }

    /**
     * Display borrowed books for a student
     * @param borrowedBooks List of books borrowed by the student
     */
    private void displayBorrowedBooks(ListADT<Book> borrowedBooks) {
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("%-5s | %-25s | %-20s | %-15s | %-10s%n",
                "ID", "Title", "Author", "Category", "Borrowed Date");
        System.out.println("-----------------------------------------------------------------------------------");
        for (int i = 0; i < borrowedBooks.len(); i++) {
            Book book = borrowedBooks.get(i);
            System.out.printf("%-5d | %-25s | %-20s | %-15s | %s%n",
                    book.getId(),
                    book.getTitle().length() > 25 ? book.getTitle().substring(0, 22) + "..." : book.getTitle(),
                    book.getAuthor().length() > 20 ? book.getAuthor().substring(0, 17) + "..." : book.getAuthor(),
                    book.getCategory().length() > 15 ? book.getCategory().substring(0, 12) + "..." : book.getCategory(),
                    book.getBorrowedDate());
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }

    /**
     * Get integer input from user with error handling
     * @return The integer value entered by the user
     */
    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    /**
     * Process the return of a borrowed book
     * Handles penalty calculation and payment if necessary
     * @param bookID The ID of the book to return
     * @param studentID The ID of the student returning the book
     * @return A ReturnResult object containing success status and detailed message
     */
    public ReturnResult returnBook(int bookID, int studentID) {
        // Step 1: Verify book exists
        ListADT<Book> books = bookDataService.search(book -> book.getId() == bookID);
        if (books.len() != 1) {
            return new ReturnResult(false, "Error: Book with ID " + bookID + " does not exist.");
        }
        Book bookToReturn = books.get(0);

        // Step 2: Verify student exists
        ListADT<UserInfo> users = userDataService.search(user -> user.getId() == studentID);
        if (users.len() != 1) {
            return new ReturnResult(false, "Error: User with ID " + studentID + " does not exist.");
        }

        // Step 3: Verify user is a student
        if (!(users.get(0) instanceof Student)) {
            return new ReturnResult(false, "Error: User with ID " + studentID + " is not a student.");
        }
        Student returningStudent = (Student) users.get(0);

        // Step 4: Check if the student actually borrowed this book
        if (!bookToReturn.isBorrowed() || bookToReturn.getPersonInBorrowed() == null ||
                bookToReturn.getPersonInBorrowed().getId() != studentID) {
            return new ReturnResult(false, "Error: Student " + returningStudent.getName() + 
                    " did not borrow this book.");
        }

        // Step 5: Calculate penalty fee using calPenaltyFee method
        boolean isBroken = askIfBookIsBroken();
        ListADT<Double> penaltyFees = borrowBookService.calPenaltyFee(bookID, isBroken);
        double totalPenalty = penaltyFees.get(0) + penaltyFees.get(1);

        // Step 6: Handle penalty payment if penalty exists
        if (totalPenalty > 0) {
            System.out.println("\n--- Penalty Fee Details ---");
            System.out.printf("Late Return Penalty: RM%.2f%n", penaltyFees.get(0));
            if (isBroken) {
                System.out.printf("Broken Book Penalty: RM%.2f%n", penaltyFees.get(1));
            }
            System.out.printf("Total Penalty: RM%.2f%n", totalPenalty);

            System.out.print("\nDo you want to pay the penalty now? (yes/no): ");
            String payChoice = scanner.nextLine().trim().toLowerCase();

            if (payChoice.equals("yes")) {
                if (!processPenaltyPayment(bookID, studentID, totalPenalty, isBroken)) {
                    return new ReturnResult(false, "Error: Payment failed. Please try again.");
                }
            } else {
                return new ReturnResult(false, "Error: Penalty must be paid before returning the book.");
            }
        }

        // Step 7: Execute the return transaction
        try {
            boolean returnSuccess = borrowBookService.returnBook(bookID, studentID, isBroken);
            
            if (returnSuccess) {
                String successMessage = String.format(
                        "Success: Student %s successfully returned \"%s\" by %s.",
                        returningStudent.getName(),
                        bookToReturn.getTitle(),
                        bookToReturn.getAuthor()
                );
                if (totalPenalty > 0) {
                    successMessage += String.format(" Penalty paid: RM%.2f", totalPenalty);
                }
                return new ReturnResult(true, successMessage);
            } else {
                return new ReturnResult(false, "Error: Failed to complete return transaction. Please try again.");
            }
        } catch (Exception e) {
            return new ReturnResult(false, "Error: " + e.getMessage());
        }
    }

    /**
     * Ask the user if the book is damaged/broken
     * @return true if book is broken, false otherwise
     */
    private boolean askIfBookIsBroken() {
        System.out.print("\nIs the book damaged or broken? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }

    /**
     * Process penalty payment from the student
     * @param bookID The ID of the book
     * @param studentID The ID of the student
     * @param totalPenalty The total penalty amount
     * @param isBroken Whether the book is broken
     * @return true if payment is successful, false otherwise
     */
    private boolean processPenaltyPayment(int bookID, int studentID, double totalPenalty, boolean isBroken) {
        System.out.print("\nEnter payment amount (RM): ");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            
            if (amount < 0) {
                System.out.println("Error: Payment amount cannot be negative.");
                return false;
            }
            
            double change = borrowBookService.payingPenalty(bookID, studentID, amount, isBroken);
            
            if (change >= 0) {
                System.out.printf("[SUCCESS] Payment received. Change: RM%.2f%n", change);
                return true;
            } else {
                System.out.println("Error: Insufficient payment. Please pay the full penalty amount.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid payment amount. Please enter a valid number.");
            return false;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Inner class to hold return transaction result
     */
    public static class ReturnResult {
        private boolean success;
        private String message;

        public ReturnResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
