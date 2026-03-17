package Transaction.Book;

import java.util.Scanner;

import ADT.ListADT;

public class Register {
    private final ListADT<Book> bookList;
    private final Scanner scanner;

    public Register() {
        this(new Scanner(System.in));
    }

    public Register(Scanner scanner) {
        this.scanner = scanner;
        this.bookList = new ListADT<>();
    }

    public void registerBook() {
        String title = promptNonEmpty("Enter book title: ");
        String author = promptNonEmpty("Enter book author: ");
        int year = promptYear();
        double price = promptPrice();

        register(new Book(title, author, year, price));
    }

    public void register(Book book) {
        bookList.add(book);
        System.out.println("Book registered: " + book.getTitle());
    }

    public ListADT<Book> getBookList() {
        return bookList;
    }

    private String promptNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private int promptYear() {
        while (true) {
            System.out.print("Enter publication year: ");
            String input = scanner.nextLine().trim();
            try {
                int year = Integer.parseInt(input);
                if (year > 0) {
                    return year;
                }
                System.out.println("Year must be positive. Please try again.");
            } catch (NumberFormatException ex) {
                System.out.println("Year must be a number. Please try again.");
            }
        }
    }

    private double promptPrice() {
        while (true) {
            System.out.print("Enter book price: RM");
            String input = scanner.nextLine().trim();
            try {
                double price = Double.parseDouble(input);
                if (price >= 0) {
                    return price;
                }
                System.out.println("Price must be non-negative. Please try again.");
            } catch (NumberFormatException ex) {
                System.out.println("Price must be a number. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        Register register = new Register();
        Book book1 = new Book("Java Programming", "John Doe", 2020, 29.99);
        Book book2 = new Book("Data Structures", "Jane Smith", 2019, 35.50);
        register.register(book1);
        register.register(book2);
        System.out.println("Total books: " + register.getBookList().len());
    }
}
