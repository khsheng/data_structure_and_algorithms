package Transaction;

import ADT.ListADT;
import data_management.entity.Book;
import java.util.Scanner;

/**
 * Utility class for displaying books with pagination
 * Handles paginated display of books with next/back navigation
 */
public class BookListPagination {
    private static final int BOOKS_PER_PAGE = 10;
    private final Scanner scanner;

    public BookListPagination(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Display all books with pagination
     * Allows user to navigate through pages using 'next'/'back' commands, or Enter to continue
     * @param allBooks The list of books to display
     */
    public void displayBooksWithPagination(ListADT<Book> allBooks) {
        if (allBooks.len() == 0) {
            System.out.println("No books available in the system.");
            return;
        }

        int totalPages = (int) Math.ceil((double) allBooks.len() / BOOKS_PER_PAGE);
        int currentPage = 1;
        boolean navigating = true;

        while (navigating) {
            displayBookPage(allBooks, currentPage, totalPages);

            System.out.print("\nType 'next' to next page, 'back' to last page, or press Enter to continue: ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "next":
                    if (currentPage < totalPages) {
                        currentPage++;
                    } else {
                        System.out.println("Already on the last page.");
                    }
                    break;
                case "back":
                    if (currentPage > 1) {
                        currentPage--;
                    } else {
                        System.out.println("Already on the first page.");
                    }
                    break;
                case "":
                    // Empty input (just pressing Enter) - exit pagination
                    navigating = false;
                    break;
                default:
                    System.out.println("Invalid command. Type 'next', 'back', or press Enter to continue.");
            }
        }
    }

    /**
     * Display a single page of books
     * @param allBooks The complete list of books
     * @param currentPage The current page number (1-indexed)
     * @param totalPages The total number of pages
     */
    private void displayBookPage(ListADT<Book> allBooks, int currentPage, int totalPages) {
        int startIndex = (currentPage - 1) * BOOKS_PER_PAGE;
        int endIndex = Math.min(startIndex + BOOKS_PER_PAGE, allBooks.len());

        System.out.println("\nAvailable Books (Page " + currentPage + " of " + totalPages + "):");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("%-5s | %-25s | %-20s | %-15s | %-10s%n",
                "ID", "Title", "Author", "Category", "Price");
        System.out.println("-----------------------------------------------------------------------------------");
        for (int i = startIndex; i < endIndex; i++) {
            Book book = allBooks.get(i);
            System.out.printf("%-5d | %-25s | %-20s | %-15s | RM%-8.2f%n",
                    book.getId(),
                    book.getTitle().length() > 25 ? book.getTitle().substring(0, 22) + "..." : book.getTitle(),
                    book.getAuthor().length() > 20 ? book.getAuthor().substring(0, 17) + "..." : book.getAuthor(),
                    book.getCategory().length() > 15 ? book.getCategory().substring(0, 12) + "..." : book.getCategory(),
                    book.getPrice());
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }
}
