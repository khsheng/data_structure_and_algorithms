package util;

import ADT.ListADT;
import data_management.entity.Book;
import data_management.service.BookDataService;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.*;

public class BookDisplay extends DisplayTableAction<Book>{
    BookDataService dataService = new BookDataService();

    public BookDisplay(ListADT<Book> displayList) {
        super(displayList);
    }

    // Sort
    @Override
    public String AttributeMenu() {
        System.out.println("\n====== Sort By ======");
        System.out.println("1. ID");
        System.out.println("2. Title");
        System.out.println("3. Author");
        System.out.println("4. Category");
        System.out.println("5. Price");
        System.out.println("6. Borrowed");
        System.out.println("7. Borrowed By");
        System.out.println("8. Borrowed Date");
        System.out.println("9. Penalty Paid");
        System.out.println("10. Broken");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");

        String input = scanner.nextLine();
        return input;
    }

    // Return Comparator based on option and order
    @Override
    public Comparator<Book> getComparatorByOption(String option, boolean ascending) {
        Comparator<Book> comparator;

        switch (option) {
            case "1": // ID
                comparator = Comparator.comparing(Book::getId);
                break;
            case "2": // Title
                comparator = Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER);
                break;
            case "3": // Author
                comparator = Comparator.comparing(Book::getAuthor, String.CASE_INSENSITIVE_ORDER);
                break;
            case "4": // Category
                comparator = Comparator.comparing(Book::getCategory, String.CASE_INSENSITIVE_ORDER);
                break;
            case "5": // Price
                comparator = Comparator.comparingDouble(Book::getPrice);
                break;
            case "6": // Borrowed
                comparator = Comparator.comparing(Book::isBorrowed);
                break;
            case "7": // Borrowed By
                comparator = Comparator.comparing(
                    b -> b.getPersonInBorrowed() != null ? b.getPersonInBorrowed().getName() : "",
                    String.CASE_INSENSITIVE_ORDER
                );
                break;
            case "8": // Borrowed Date
                comparator = Comparator.comparing(Book::getBorrowedDate,
                    Comparator.nullsLast(Comparator.naturalOrder()));
                break;
            case "9": // Penalty Paid
                comparator = Comparator.comparing(Book::isPenaltyPayed);
                break;
            case "10": // Broken
                comparator = Comparator.comparing(Book::isBorken);
                break;
            default:
                return null;
        }

        // Flip comparator if descending
        return ascending ? comparator : comparator.reversed();
    }

    @Override
    public Predicate<Book> getPredicateByOption(String option) {
        switch (option) {
            case "1": // ID
                System.out.print("Enter ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                return book -> book.getId() == id;

            case "2": // Title
                System.out.print("Enter Title keyword: ");
                String title = scanner.nextLine();
                return book -> book.getTitle().toLowerCase().contains(title.toLowerCase());

            case "3": // Author
                System.out.print("Enter Author keyword: ");
                String author = scanner.nextLine();
                return book -> book.getAuthor().toLowerCase().contains(author.toLowerCase());

            case "4": // Category
                System.out.print("Enter Category keyword: ");
                String category = scanner.nextLine();
                return book -> book.getCategory().toLowerCase().contains(category.toLowerCase());

            case "5": // Price (range)
                System.out.print("Enter minimum price: ");
                double min = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter maximum price: ");
                double max = Double.parseDouble(scanner.nextLine());
                return book -> book.getPrice() >= min && book.getPrice() <= max;

            case "6": // Borrowed status
                System.out.print("Search borrowed books? (true/false): ");
                boolean borrowed = Boolean.parseBoolean(scanner.nextLine());
                return book -> book.isBorrowed() == borrowed;

            case "7": // Borrowed By
                System.out.print("Enter borrower name: ");
                String borrower = scanner.nextLine();
                return book -> book.getPersonInBorrowed() != null &&
                            book.getPersonInBorrowed().getName().equalsIgnoreCase(borrower);

            case "8": // Borrowed Date
                System.out.print("Enter borrowed date (YYYY-MM-DD): ");
                LocalDate date = LocalDate.parse(scanner.nextLine());
                return book -> date.equals(book.getBorrowedDate());

            case "9": // Penalty Paid
                System.out.print("Search penalty paid? (true/false): ");
                boolean penalty = Boolean.parseBoolean(scanner.nextLine());
                return book -> book.isPenaltyPayed() == penalty;

            case "10": // Broken
                System.out.print("Search broken books? (true/false): ");
                boolean broken = Boolean.parseBoolean(scanner.nextLine());
                return book -> book.isBorken() == broken;

            default:
                System.out.println("Invalid option.");
                return book -> false; // no matches
        }
    }

    @Override
    public void tableLayout(int startIndex, int endIndex, ListADT<Book> copyBookList) {
        // Table header
        System.out.printf("%-5s %-25s %-20s %-15s %-10s %-10s %-15s %-15s %-15s %-10s%n",
                "ID", "Title", "Author", "Category", "Price", "Borrowed",
                "Borrowed By", "Borrowed Date", "Penalty Paid", "Broken");

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

        // Table rows for this page
        for (int i = startIndex; i < endIndex; i++) {
            Book book = copyBookList.get(i);

            String borrower = book.isBorrowed() && book.getPersonInBorrowed() != null
                    ? book.getPersonInBorrowed().getName()
                    : "-";

            String borrowedDate = book.getBorrowedDate() != null ? book.getBorrowedDate().toString() : "-";

            String penaltyPaidMessage;
            if (book.getPenaltyFee() == 0.0) {
                penaltyPaidMessage = "-";
            } else {
                penaltyPaidMessage = book.isPenaltyPayed() ? "Yes" : "No";
            }

            System.out.printf("%-5d %-25s %-20s %-15s RM%-9.2f %-10s %-15s %-15s %-15s %-10s%n",
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCategory(),
                    book.getPrice(),
                    book.isBorrowed() ? "Yes" : "No",
                    borrower,
                    borrowedDate,
                    penaltyPaidMessage,
                    book.isBorken() ? "Yes" : "No");
        }
    }

    @Override
    public ListADT<Book> sort (Comparator<Book> comparator){
        return dataService.sort(comparator);
    }
    
    @Override
    public ListADT<Book> search (Predicate<Book> parameter){
        return dataService.search(parameter);
    }
}
