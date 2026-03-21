package data_management.service;

import ADT.ListADT;
import data_management.entity.*;

import java.util.Comparator;
import java.util.function.*;

import util.BookDisplay;
import util.Testing;

public class BookDataService implements CrudService<Book> {
    public static ListADT<Book> bookList = new ListADT<>();

    @Override
    public void add(Book book) {
        bookList.add(book);
    }

    @Override
    public void add(ListADT<Book> books) {
        for (int i = 0; i < books.len(); i++) {
            bookList.add(books.get(i));
        }
    }

    @Override
    public void remove(int index) {
        bookList.remove(index);
    }

    @Override
    public void update(int index, Consumer<Book> set) {
        Book updateBook = bookList.get(index);
        set.accept(updateBook);
    }

    @Override
    public ListADT<Book> search(Predicate<Book> parameters) {
        ListADT<Integer> matchedIndex = bookList.findAll(parameters);
        ListADT<Book> result = new ListADT<>();
   
        if (matchedIndex.get(0) == -1){
            return result;
        }

        for (int i = 0; i < matchedIndex.len(); i++) {
            result.add(bookList.get(matchedIndex.get(i)));
        }

        return result;
    }

    public ListADT<Book> sort(Comparator<Book> comparator) {
        bookList.sort(comparator);
        return bookList;
    }

    public boolean isAvailable(int bookID) {
        ListADT<Integer> bookIndex = bookList.findAll(book -> book.getId() == bookID);
        if (bookIndex.len() == 1) {
            return !bookList.get(bookIndex.get(0)).isBorrowed();
        } else if (bookIndex.len() == 0) {
            throw new IllegalArgumentException("Book with the specified ID does not exist.");
        } else {
            throw new IllegalStateException("Unexpected number of books found.");
        }
    }

    public ListADT<Book> listAvailableBook(){
        ListADT<Book> availableBooks = new ListADT<>();
        for (int i = 0; i < bookList.len(); i++) {
            if (!bookList.get(i).isBorrowed()) {
                availableBooks.add(bookList.get(i));
            }
        }
        return availableBooks;
    }

    public void displayTable() {
        ListADT<Book> copyBookList = bookList.copy();

        int pageSize = 5;
        if (copyBookList.len() == 0) {
            System.out.println("No books available.");
            return;
        }

        int currentPage = 1;

        while (true) {
            int totalRecords = copyBookList.len();
            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, totalRecords);
            BookDisplay bookDisplay = new BookDisplay(currentPage, totalPages);
            String input;

            tableLayout(startIndex, endIndex, copyBookList);

            // Call action and update currentPage
            input = bookDisplay.tableActionMenu();

            switch (input) {
                case "1":
                    currentPage = bookDisplay.nextPage();
                    break;

                case "2":
                    currentPage = bookDisplay.previousPage();
                    break;

                case "3":
                    String sortBy = bookDisplay.AttributeMenu();
                    Boolean ascending = bookDisplay.byAscending();

                    Comparator comparator = bookDisplay.getComparatorByOption(sortBy, ascending);
                    copyBookList = sort(comparator);
                    break;

                case "4":
                    String searchBy = bookDisplay.AttributeMenu();
                    Predicate<Book> predicate = bookDisplay.getPredicateByOption(searchBy);

                    copyBookList = search(predicate);
                    break;

                default:
                    return;
            } 

            System.out.println();
        }
    }

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
    public String toString() {
        if (bookList.len() == 0) return "[]";

        StringBuilder newString = new StringBuilder();
        newString.append("[\n");
        for (int i = 0; i < bookList.len(); i++){
            newString.append("  {\n");

            newString.append("      ID: ").append(bookList.get(i).getId()).append(", \n");
            newString.append("      Title: ").append(bookList.get(i).getTitle()).append(", \n");
            newString.append("      Author: ").append(bookList.get(i).getAuthor()).append(", \n");
            newString.append("      Category: ").append(bookList.get(i).getCategory()).append(", \n");
            newString.append("      Price: RM ").append(String.format("%.2f", bookList.get(i).getPrice())).append(", \n");
            newString.append("      Borrowed: ").append(bookList.get(i).isBorrowed()).append("\n");

            if (bookList.get(i).isBorrowed()) {
                newString.append("      Borrowed By: ").append(bookList.get(i).getPersonInBorrowed().getName()).append("\n");
            }

            newString.append("  },\n");
        }
        newString.delete(newString.length() - 2, newString.length());
        newString.append("\n]");
        return newString.toString();
    }  


    public static void main(String[] args) {
        BookDataService bookDataService = new BookDataService();
        Testing.addTestBooks(bookDataService);

        UserDataService userDataService = new UserDataService();
        Testing.addTestUsers(userDataService);

        bookDataService.displayTable();
    }
}
