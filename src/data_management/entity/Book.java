package data_management.entity;

import java.time.LocalDate;
import java.util.Scanner;


public class Book {
    private static int counter = 1;

    private int id;
    private String title;
    private String author;
    private String category;
    private double price;
    private boolean isBorrowed;
    private LocalDate borrowedDate = null;
    private Student personInBorrowed = null;
    private boolean isPenaltyPayed = false;
    private boolean isBroken = false;

    private static final int BORROW_DURATION_DAYS = 7;

    public Book(String title, String author, double price, String category) {
        this.id = counter++;
        this.title = title;
        this.author = author;
        this.category = category;
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

    public boolean isBroken() {
        return this.isBroken;
    }

    public Student getPersonInBorrowed() {
        return this.personInBorrowed;
    }

    public String getCategory() {
        return this.category;
    }

    public LocalDate getBorrowedDate() {
        return this.borrowedDate;
    }

    public LocalDate getDueDate() {
        if (this.borrowedDate == null) {
            return null;
        }
        return this.borrowedDate.plusDays(BORROW_DURATION_DAYS);
    }

    public double getPenaltyFee() {
        if (this.borrowedDate == null) {
            return 0.0;
        }

        LocalDate dueDate = this.getDueDate();
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(dueDate)) {
            long overdueDays = java.time.temporal.ChronoUnit.DAYS.between(dueDate, currentDate);
            return (double) overdueDays * 1.0; // Assuming a penalty fee of RM1 per day
        }
        return 0.0;
    }

    public boolean isPenaltyPayed() {
        return this.isPenaltyPayed;
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

    public void setBorrowedDate() {
        this.borrowedDate = LocalDate.now();
    }

    public void resetBorrowedDate() {
        this.borrowedDate = null;
    }

    public void setBorrowed(boolean borrowed) {
        this.isBorrowed = borrowed;
    }

    public void setBroken(boolean isBroken) {
        this.isBroken = isBroken;
    }

    public void setPersonInBorrowed(Student personInBorrowed) {
        this.personInBorrowed = personInBorrowed;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setIsPenaltyPayed(boolean isPenaltyPayed) {
        this.isPenaltyPayed = isPenaltyPayed;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        String password = "khs";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter password to set borrowed date:");
        String enteredPassword = scanner.nextLine();

        if (enteredPassword.equals(password)) {
            this.borrowedDate = borrowedDate;
        } else {
            System.out.println("Incorrect password.");
            throw new SecurityException("Unauthorized access to set borrowed date.");
        }
    }

    @Override
    public String toString() {
        StringBuilder newString = new StringBuilder();
        newString.append("ID: ").append(this.id).append("\n");
        newString.append("Title: ").append(this.title).append("\n");
        newString.append("Author: ").append(this.author).append("\n");
        newString.append("Category: ").append(this.category).append("\n");
        newString.append("Price: RM ").append(String.format("%.2f", this.price)).append("\n");
        newString.append("Borrowed: ").append(this.isBorrowed).append("\n");

        if (isBorrowed) {
            newString.append("Borrowed By: ").append(this.personInBorrowed.getName()).append("\n");
            newString.append("Borrowed Date: ").append(this.borrowedDate).append("\n");
            newString.append("Due Date: ").append(this.getDueDate()).append("\n");
            newString.append("Penalty Fee: RM ").append(String.format("%.2f", this.getPenaltyFee())).append("\n");
            newString.append("Penalty Payed: ").append(this.isPenaltyPayed()).append("\n");
        }

        return newString.toString();
    }
}
