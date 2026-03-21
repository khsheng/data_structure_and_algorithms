package Transaction.Book;

import data_management.entity.Book;
import data_management.service.BookDataService;
import ADT.ListADT;
import Transaction.BookListPagination;

import java.util.Scanner;

public class Delete {
    private final Scanner scanner;
    private static final BookDataService bookDataService = new BookDataService();

    private BookListPagination paginationDisplay;

    public Delete() {
        this(new Scanner(System.in));
    }

    public Delete(Scanner scanner) {
        this.scanner = scanner;
        this.paginationDisplay = new BookListPagination(scanner);
    }

    /**
     * Delete an existing book from the system
     * Displays all books and allows user to select and delete a book
     */
    public void deleteBook() {
        try {
            System.out.println("\n--- Delete Book ---");
            
            // Display all books
            displayAllBooks();
            
            // Get book ID to delete
            System.out.print("Enter book ID to delete: ");
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
            
            Book bookToDelete = searchResult.get(0);
            
            // Confirm deletion
            System.out.println("\nBook to be deleted:");
            System.out.printf("  Title: %s%n  Author: %s%n  Category: %s%n  Price: RM%.2f%n",
                    bookToDelete.getTitle(), bookToDelete.getAuthor(), 
                    bookToDelete.getCategory(), bookToDelete.getPrice());
            
            System.out.print("\nAre you sure you want to delete this book? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (!confirmation.equals("yes")) {
                System.out.println("x Deletion cancelled.");
                return;
            }
            
            // Find the index and delete
            int index = findBookIndex(bookId);
            if (index >= 0) {
                bookDataService.remove(index);
                System.out.println("[SUCCESS] Book deleted successfully!");
                System.out.println("  Title: " + bookToDelete.getTitle());
                System.out.println("  Author: " + bookToDelete.getAuthor());
                System.out.println("  Category: " + bookToDelete.getCategory());
                System.out.println("  Price: RM" + String.format("%.2f", bookToDelete.getPrice()));
            } else {
                System.out.println("Error: Failed to delete the book.");
            }
            
        } catch (Exception e) {
            System.out.println("Error deleting book: " + e.getMessage());
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
}
