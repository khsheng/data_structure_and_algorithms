package data_management.entity;

public class Book {
    private static int counter = 1;

    private int id;
    private String title;
    private String author;
    private double price;
    private boolean isBorrowed;
    private String personInBorrowed = null;

    public Book(String title, String author, double price) {
        this.id = counter++;
        this.title = title;
        this.author = author;
        this.price = price;
        this.isBorrowed = false;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public double getPrice() {
        return this.price;
    }

    public boolean isBorrowed() {
        return this.isBorrowed;
    }

    public String getPersonInBorrowed() {
        return this.personInBorrowed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean borrow(String person) {
        if (!isBorrowed) {
            this.isBorrowed = true;
            this.personInBorrowed = person;
            return true;
        }

        return false;
    }

    public boolean returnBook() {
        if (isBorrowed) {
            this.isBorrowed = false;
            this.personInBorrowed = null;
            return true;
        }

        return false;
    }
}
