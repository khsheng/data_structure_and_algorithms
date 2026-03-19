package Transaction;

import Transaction.Book.Register;
import Transaction.Book.Update;
import Transaction.Book.Delete;
import java.util.Scanner;


public class TransactionMenu {
    private final Scanner scanner;
    private final Register register;
    private final Update update;
    private final Delete delete;

    public TransactionMenu() {
        this(new Scanner(System.in));
    }

    TransactionMenu(Scanner scanner) {
        this.scanner = scanner;
        this.register = new Register(scanner);
        this.update = new Update(scanner);
        this.delete = new Delete(scanner);
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
                    register.registerBook();
                    break;
                case "2":
                    update.updateBook();
                    break;
                case "3":
                    System.out.println("Display overview / selected book selected (feature pending).");
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


}
