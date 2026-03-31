package data_management.service;

import ADT.ListADT;
import data_management.entity.*;
import java.util.Comparator;
import java.util.function.*;
import util.BookDisplay;
import util.DisplayTableAction;
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

    @Override
    public ListADT<Book> search(ListADT<Book> list,Predicate<Book> parameters) {
        ListADT<Book> copyList = list.copy();        
        ListADT<Integer> matchedIndex = copyList.findAll(parameters);
        ListADT<Book> result = new ListADT<>();

   
        if (matchedIndex.get(0) == -1){
            return result;
        }

        for (int i = 0; i < matchedIndex.len(); i++) {
            result.add(copyList.get(matchedIndex.get(i)));
        }

        return result;
    }

    public ListADT<Book> sort(Comparator<Book> comparator) {
        bookList.sort(comparator);
        return bookList;
    }

    public ListADT<Book> sort(ListADT<Book> list, Comparator<Book> comparator) {
        ListADT<Book> copyList = list.copy();

        copyList.sort(comparator);
        return copyList;
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

        DisplayTableAction<Book> displayTable = new BookDisplay(bookDataService.search(b -> true));
        displayTable.displayTable();


    }
}
