package Transaction;

import java.util.Scanner;

import Transaction.Book.Register;

public class TransactionMenu {
    private final Scanner scanner;
    private final Register register;

    public TransactionMenu() {
        this(new Scanner(System.in));
    }

    TransactionMenu(Scanner scanner) {
        this(scanner, new Register(scanner));
    }

    TransactionMenu(Scanner scanner, Register register) {
        this.scanner = scanner;
        this.register = register;
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
                    registerBook();
                    break;
                case "2":
                    System.out.println("Update book information selected (feature pending).");
                    break;
                case "3":
                    System.out.println("Display overview / selected book selected (feature pending).");
                    break;
                case "4":
                    System.out.println("Delete book selected (feature pending).");
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

    private void registerBook() {
        register.registerBook();
    }
}
