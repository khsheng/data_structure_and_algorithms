package Transaction.Borrow;

import ADT.ListADT;
import data_management.entity.Book;
import data_management.entity.Student;
import data_management.entity.UserInfo;
import data_management.service.BookDataService;
import data_management.service.BorrowBook;
import data_management.service.UserDataService;
import data_management.service.BookListPagination;
import java.util.Scanner;

public class Borrow {
    private BookDataService bookDataService;
    private UserDataService userDataService;
    private BorrowBook borrowBookService;
    private BookListPagination paginationDisplay;
    private Scanner scanner;

    public Borrow() {
        this(new Scanner(System.in));
    }

    public Borrow(Scanner scanner) {
        this.scanner = scanner;
        this.bookDataService = new BookDataService();
        this.userDataService = new UserDataService();
        this.borrowBookService = new BorrowBook();
        this.paginationDisplay = new BookListPagination(scanner);
    }

    /**
     * Performs qualification checks before allowing a student to borrow a book
     * @param bookID The ID of the book to borrow
     * @param studentID The ID of the student borrowing the book
     * @return A BorrowResult object containing success status and detailed message
     */
    public BorrowResult borrowBook(int bookID, int studentID) {
        // Step 1: Verify book exists and is available
        ListADT<Book> books = bookDataService.search(book -> book.getId() == bookID);
        if (books.len() != 1) {
            return new BorrowResult(false, "Error: Book with ID " + bookID + " does not exist.");
        }
        Book bookToBorrow = books.get(0);

        // Step 2: Verify student exists
        ListADT<UserInfo> users = userDataService.search(user -> user.getId() == studentID);
        if (users.len() != 1) {
            return new BorrowResult(false, "Error: User with ID " + studentID + " does not exist.");
        }

        // Step 3: Verify user is a student
        if (!(users.get(0) instanceof Student)) {
            return new BorrowResult(false, "Error: User with ID " + studentID + " is not a student.");
        }
        Student borrowingStudent = (Student) users.get(0);

        // Step 4: Check if student is blacklisted
        if (borrowingStudent.isBlacklisted()) {
            return new BorrowResult(false, "Error: Student " + borrowingStudent.getName() + 
                    " is blacklisted and cannot borrow books.");
        }

        // Step 5: Check if student has unpaid penalties
        if (hasUnpaidPenalties(borrowingStudent)) {
            return new BorrowResult(false, "Error: Student " + borrowingStudent.getName() + 
                    " has unpaid penalty fees and cannot borrow books.");
        }

        // Step 6: Check quantity of books borrowed (max 5)
        if (!borrowingStudent.canBorrow()) {
            return new BorrowResult(false, "Error: Student " + borrowingStudent.getName() + 
                    " has reached maximum borrow limit (" + borrowingStudent.getRemainingBorrowLimit() + 
                    " remaining). Cannot borrow more books.");
        }

        // Step 7: Check if book is available
        if (bookToBorrow.isBorrowed()) {
            return new BorrowResult(false, "Error: Book \"" + bookToBorrow.getTitle() + 
                    "\" is not available for borrowing.");
        }

        // All qualifications passed - call BorrowBook service to record in both UserData and BookData
        try {
            boolean borrowSuccess = borrowBookService.borrowBook(bookID, studentID);
            
            if (borrowSuccess) {
                String successMessage = String.format(
                        "Success: Student %s successfully borrowed \"%s\" by %s. Due date: %s",
                        borrowingStudent.getName(),
                        bookToBorrow.getTitle(),
                        bookToBorrow.getAuthor(),
                        bookToBorrow.getDueDate()
                );
                return new BorrowResult(true, successMessage);
            } else {
                return new BorrowResult(false, "Error: Failed to complete borrow transaction. Please try again.");
            }
        } catch (Exception e) {
            return new BorrowResult(false, "Error: " + e.getMessage());
        }
    }

    private boolean hasUnpaidPenalties(Student student) {
        ListADT<Book> borrowedBooks = student.getBorrowedBooks();
        
        for (int i = 0; i < borrowedBooks.len(); i++) {
            Book book = borrowedBooks.get(i);
            // Check if book has penalty and penalty is not paid
            if (book.getPenaltyFee() > 0 && !book.isPenaltyPayed()) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Display all available books (not borrowed) with pagination
     */
    public void displayAvailableBooks() {
        ListADT<Book> allBooks = bookDataService.search(book -> true);
        ListADT<Book> availableBooks = new ListADT<>();
        
        for (int i = 0; i < allBooks.len(); i++) {
            if (!allBooks.get(i).isBorrowed()) {
                availableBooks.add(allBooks.get(i));
            }
        }
        
        paginationDisplay.displayBooksWithPagination(availableBooks);
    }

    /**
     * Display all books (both available and borrowed) with pagination
     */
    public void displayAllBooks() {
        ListADT<Book> allBooks = bookDataService.search(book -> true);
        paginationDisplay.displayBooksWithPagination(allBooks);
    }

    /**
     * Inner class to hold borrow transaction result
     */
    public static class BorrowResult {
        private boolean success;
        private String message;

        public BorrowResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return message;
        }
    }
}
