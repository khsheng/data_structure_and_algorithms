package data_management.entity;

import java.time.LocalDate;

public class BookHistory {
    public enum TransactionType {
        BORROW,
        RETURN
    }

    private int bookID;
    private int studentID;
    private TransactionType action;
    private LocalDate date;

    public BookHistory(int bookID, int studentID, TransactionType action, LocalDate date) {
        this.bookID = bookID;
        this.studentID = studentID;
        this.action = action;
        this.date = date;
    }

    // Getters
    public int getBookID() {
        return bookID;
    }

    public int getStudentID() {
        return studentID;
    }

    public TransactionType getAction() {
        return action;
    }

    public LocalDate getDate() {
        return date;
    }

    // Setters
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setAction(TransactionType action) {
        this.action = action;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("BookID: %d, StudentID: %d, Action: %s, Date: %s",
                bookID, studentID, action, date);
    }
}
