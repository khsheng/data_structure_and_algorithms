package data_management.entity;

import ADT.ListADT;

public class Student extends UserInfo {
    private String program;
    private ListADT<Book> borrowedBooks;
    private static final int MAXBORROWS = 5;

    public Student(String name, int age, String program) {
        super(name, age, "student");
        this.program = program;
        this.borrowedBooks = new ListADT<>();
    }

    public String getProgram() {
        return this.program;
    }

    public ListADT<Book> getBorrowedBooks() {
        return this.borrowedBooks;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public boolean canBorrow() {
        return borrowedBooks.len() < MAXBORROWS;
    }

    public int getRemainingBorrowLimit() {
        return MAXBORROWS - borrowedBooks.len();
    }

    public void addBorrowedBooks(Book borrowBook) {
        this.borrowedBooks.add(borrowBook);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Age: %d, Program: %s, Borrowed Books: %d", this.id, this.name, this.age, this.program, this.borrowedBooks.len());
    }
}
