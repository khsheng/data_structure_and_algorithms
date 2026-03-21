package Transaction.Book;

import data_management.entity.Book;
import data_management.service.BookDataService;

import java.util.Scanner;

public class Register {
    private final Scanner scanner;
    private static final BookDataService bookDataService = new BookDataService();

    public Register() {
        this(new Scanner(System.in));
    }

    public Register(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Register a new book to the system
     * Prompts user for book details: title, author, price, and category
     */
    public void registerBook() {
        try {
            System.out.println("\n--- Register New Book ---");
            
            System.out.print("Enter book title: ");
            String title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Error: Title cannot be empty.");
                return;
            }
            
            System.out.print("Enter author name: ");
            String author = scanner.nextLine().trim();
            if (author.isEmpty()) {
                System.out.println("Error: Author name cannot be empty.");
                return;
            }
            
            System.out.print("Enter book price (RM): ");
            double price;
            try {
                price = Double.parseDouble(scanner.nextLine().trim());
                if (price < 0) {
                    System.out.println("Error: Price cannot be negative.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid price format. Please enter a valid number.");
                return;
            }
            
            System.out.print("Enter book category: ");
            String category = scanner.nextLine().trim();
            if (category.isEmpty()) {
                System.out.println("Error: Category cannot be empty.");
                return;
            }
            
            // Create and add book
            Book newBook = new Book(title, author, price, category);
            bookDataService.add(newBook);
            
            System.out.println("[SUCCESS] Book registered successfully!");
            System.out.println("  Book ID: " + newBook.getId());
            System.out.println("  Title: " + newBook.getTitle());
            System.out.println("  Author: " + newBook.getAuthor());
            System.out.println("  Price: RM" + newBook.getPrice());
            System.out.println("  Category: " + newBook.getCategory());
            
        } catch (Exception e) {
            System.out.println("Error registering book: " + e.getMessage());
        }
    }
}
