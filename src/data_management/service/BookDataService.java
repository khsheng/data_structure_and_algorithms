package data_management.service;

import  ADT.ListADT;
import data_management.entity.*;

public class BookDataService {
    public ListADT<Book> bookList;

    public BookDataService() {
        bookList = new ListADT<>();
    }

    public void add(Book book) {
        bookList.add(book);
    }

    public void add(ListADT<Book> books) {
        for (int i = 0; i < books.len(); i++) {
            bookList.add(books.get(i));
        }
    }

    public void remove(int index) {
        bookList.remove(index);
    }

    public void update(int index, String title, String author, double price) {
        Book updateBook = (Book) bookList.get(index);

        updateBook.setTitle(title);
        updateBook.setAuthor(author);
        updateBook.setPrice(price);
    }

    public static void main(String[] args) {
        BookDataService bookDataService = new BookDataService();

        bookDataService.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 10.99));
        bookDataService.add(new Book("To Kill a Mockingbird", "Harper Lee", 8.99));

        System.out.println("Books in the library:");
        for (int i = 0; i < bookDataService.bookList.len(); i++) {
            Book book = bookDataService.bookList.get(i);
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " - $" + book.getPrice());
        }
    }
}
