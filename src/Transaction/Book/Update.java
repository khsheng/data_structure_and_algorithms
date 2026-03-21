package Transaction.Book;

import data_management.entity.Book;
import data_management.service.BookDataService;
import ADT.ListADT;
import Transaction.BookListPagination;

import java.util.Scanner;

public class Update {
    private final Scanner scanner;
    private static final BookDataService bookDataService = new BookDataService();

    private BookListPagination paginationDisplay;

    public Update() {
        this(new Scanner(System.in));
    }

    public Update(Scanner scanner) {
        this.scanner = scanner;
        this.paginationDisplay = new BookListPagination(scanner);
    }

    /**
     * Update an existing book's information
     * Displays all books and allows user to select and update details
     */
    public void updateBook() {
        try {
            System.out.println("\n--- Update Book Information ---");
            
            // Display all books
            displayAllBooks();
            
            // Get book ID to update
            System.out.print("Enter book ID to update: ");
            int bookId;
            try {
                bookId = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid book ID format.");
                return;
            }
            
            // Search for the book
            ListADT<Book> searchResult = bookDataService.search(book -> book.getId() == bookId);
            if (searchResult.len() == 0) {
                System.out.println("Error: Book with ID " + bookId + " not found.");
                return;
            }
            
            Book selectedBook = searchResult.get(0);
            System.out.println("\nSelected Book Details:");
            System.out.printf("  ID: %d%n", selectedBook.getId());
            System.out.printf("  Title: %s%n", selectedBook.getTitle());
            System.out.printf("  Author: %s%n", selectedBook.getAuthor());
            System.out.printf("  Category: %s%n", selectedBook.getCategory());
            System.out.printf("  Price: RM%.2f%n", selectedBook.getPrice());
            
            // Show update options
            boolean updating = true;
            while (updating) {
                showUpdateMenu();
                String choice = scanner.nextLine().trim();
                
                switch (choice) {
                    case "1":
                        updateTitle(bookId, selectedBook);
                        break;
                    case "2":
                        updateAuthor(bookId, selectedBook);
                        break;
                    case "3":
                        updatePrice(bookId, selectedBook);
                        break;
                    case "4":
                        updateCategory(bookId, selectedBook);
                        break;
                    case "0":
                        updating = false;
                        System.out.println("[SUCCESS] Book updated successfully!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    private void updateTitle(int bookId, Book book) {
        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine().trim();
        if (newTitle.isEmpty()) {
            System.out.println("Error: Title cannot be empty.");
            return;
        }
        
        int index = findBookIndex(bookId);
        if (index >= 0) {
            bookDataService.update(index, b -> b.setTitle(newTitle));
            System.out.println("[SUCCESS] Title updated to: " + newTitle);
        }
    }

    private void updateAuthor(int bookId, Book book) {
        System.out.print("Enter new author: ");
        String newAuthor = scanner.nextLine().trim();
        if (newAuthor.isEmpty()) {
            System.out.println("Error: Author cannot be empty.");
            return;
        }
        
        int index = findBookIndex(bookId);
        if (index >= 0) {
            bookDataService.update(index, b -> b.setAuthor(newAuthor));
            System.out.println("[SUCCESS] Author updated to: " + newAuthor);
        }
    }

    private void updatePrice(int bookId, Book book) {
        System.out.print("Enter new price (RM): ");
        try {
            double newPrice = Double.parseDouble(scanner.nextLine().trim());
            if (newPrice < 0) {
                System.out.println("Error: Price cannot be negative.");
                return;
            }
            
            int index = findBookIndex(bookId);
            if (index >= 0) {
                bookDataService.update(index, b -> b.setPrice(newPrice));
                System.out.println("[SUCCESS] Price updated to: RM" + newPrice);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid price format.");
        }
    }

    private void updateCategory(int bookId, Book book) {
        System.out.print("Enter new category: ");
        String newCategory = scanner.nextLine().trim();
        if (newCategory.isEmpty()) {
            System.out.println("Error: Category cannot be empty.");
            return;
        }
        
        int index = findBookIndex(bookId);
        if (index >= 0) {
            bookDataService.update(index, b -> b.setCategory(newCategory));
            System.out.println("[SUCCESS] Category updated to: " + newCategory);
        }
    }

    private int findBookIndex(int bookId) {
        ListADT<Book> allBooks = bookDataService.search(book -> true);
        for (int i = 0; i < allBooks.len(); i++) {
            if (allBooks.get(i).getId() == bookId) {
                return i;
            }
        }
        return -1;
    }

    private void displayAllBooks() {
        ListADT<Book> allBooks = bookDataService.search(book -> true);
        paginationDisplay.displayBooksWithPagination(allBooks);
    }

    private void showUpdateMenu() {
        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Price");
        System.out.println("4. Category");
        System.out.println("0. Done Updating");
        System.out.print("Select an option: ");
    }
}
