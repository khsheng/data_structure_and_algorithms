package Transaction;

import ADT.ListADT;
import Transaction.Book.Register;
import Transaction.Book.Update;
import Transaction.Book.Delete;
import Transaction.Borrow.Borrow;
import Transaction.Return.Return;
import data_management.entity.Book;
import data_management.service.BookDataService;
import util.BookDisplay;
import util.DisplayTableAction;

import java.util.Scanner;

import ADT.ListADT;


public class TransactionMenu {
    private final Scanner scanner;
    private final Register register;
    private final Update update;
    private final Delete delete;
    private final Borrow borrow;
    private final Return returnTransaction;
    private final BookListPagination bookListPagination;
    private static final BookDataService bookDataService = new BookDataService();

    public TransactionMenu() {
        this(new Scanner(System.in));
    }

    public TransactionMenu(Scanner scanner) {
        this.scanner = scanner;
        this.register = new Register(scanner);
        this.update = new Update(scanner);
        this.delete = new Delete(scanner);
        this.borrow = new Borrow(scanner);
        this.returnTransaction = new Return(scanner);
        this.bookListPagination = new BookListPagination(scanner);
    }

    public void startForStudent(int studentID) {
        boolean running = true;
        while (running) {
            printStudentMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    borrowBookAsStudent(studentID);
                    break;
                case "2":
                    returnBookAsStudent(studentID);
                    break;
                case "3":
                    DisplayTableAction<Book> displayTable = new BookDisplay(bookDataService.search(b -> true));
                    displayTable.displayTable();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting borrow menu. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printStudentMenu() {
        System.out.println("\n--- Student Borrow Menu ---");
        System.out.println("1. Borrow Book");
        System.out.println("2. Return Book");
        System.out.println("3. Display Book");
        System.out.println("0. Back");
        System.out.print("Select an option: ");
    }

    private void borrowBookAsStudent(int studentID) {
        ListADT<Book> availableBooks = bookDataService.search(book -> !book.isBorrowed());
        BookListPagination pagination = new BookListPagination(scanner);
        pagination.displayBooksWithPagination(availableBooks);
        
        System.out.print("Enter Book ID: ");
        int bookID = getIntInput();
        
        Borrow.BorrowResult result = borrow.borrowBook(bookID, studentID);
        
        if (result.isSuccess()) {
            System.out.println("\n✓ " + result.getMessage());
        } else {
            System.out.println("\n✗ " + result.getMessage());
        }
    }

    private void returnBookAsStudent(int studentID) {
        Return.ReturnResult result = returnTransaction.returnBookAsStudent(studentID);
        
        if (result.isSuccess()) {
            System.out.println("\n✓ " + result.getMessage());
        } else {
            System.out.println("\n✗ " + result.getMessage());
        }
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    showBookMenu();
                    break;
                case "2":
                    showBorrowMenu();
                    break;
                case "3":
                    showReturnMenu();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting transaction menu. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Transaction Menu ===");
        System.out.println("1. Book Menu");
        System.out.println("2. Borrow Menu");
        System.out.println("3. Return Menu");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    private void showBookMenu() {
        boolean inBookMenu = true;
        while (inBookMenu) {
            printBookMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    register.registerBook();
                    break;
                case "2":
                    update.updateBook();
                    break;
                case "3":
                    DisplayTableAction<Book> displayTable = new BookDisplay(bookDataService.search(b -> true));
                    displayTable.displayTable();
                    break;
                case "4":
                    delete.deleteBook();
                    break;
                case "0":
                    inBookMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printBookMenu() {
        System.out.println("\n--- Book Menu ---");
        System.out.println("1. Register Book");
        System.out.println("2. Update Book Information");
        System.out.println("3. Display Overview / Selected Book");
        System.out.println("4. Delete Book");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select an option: ");
    }

    private void showBorrowMenu() {
        boolean inBorrowMenu = true;
        while (inBorrowMenu) {
            printBorrowMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    borrowBook();
                    break;
                case "0":
                    inBorrowMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printBorrowMenu() {
        ListADT<Book> availableBooks = bookDataService.search(book -> !book.isBorrowed());
        bookListPagination.displayBooksWithPagination(availableBooks);
        System.out.println("\n--- Borrow Menu ---");
        System.out.println("1. Borrow Book");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select an option: ");
    }

    private void borrowBook() {
        System.out.print("Enter Book ID: ");
        int bookID = getIntInput();
        
        System.out.print("Enter Student ID: ");
        int studentID = getIntInput();
        
        Borrow.BorrowResult result = borrow.borrowBook(bookID, studentID);
        
        if (result.isSuccess()) {
            System.out.println("\n✓ " + result.getMessage());
        } else {
            System.out.println("\n✗ " + result.getMessage());
        }
    }

    private void showReturnMenu() {
        boolean inReturnMenu = true;
        while (inReturnMenu) {
            printReturnMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    returnBook();
                    break;
                case "0":
                    inReturnMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printReturnMenu() {
        System.out.println("\n--- Return Menu ---");
        System.out.println("1. Return Book");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select an option: ");
    }

    private void returnBook() {
        Return.ReturnResult result = returnTransaction.returnBookInteractive();
        
        if (result.isSuccess()) {
            System.out.println("\n✓ " + result.getMessage());
        } else {
            System.out.println("\n✗ " + result.getMessage());
        }
    }

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

}
