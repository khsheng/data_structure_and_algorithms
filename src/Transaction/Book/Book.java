package Transaction.Book;

public class Book {
    private static int counter = 1;
    private int id;
    private String title;
    private String author;
    private int year;
    private double price;
    private boolean available;

    public Book(String title, String author, int year, double price) {
        this.id = counter++;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}