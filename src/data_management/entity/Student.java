package data_management.entity;

public class Student extends UserInfo {
    private String program;
    private int borrowedBooks;
    private static final int MAXBORROWS = 5;

    // todo: using the data type book ID as the data type of borrowedBooks instead of int, and change the name to borrowedBookIds
    public Student(String name, int age, String program, int borrowedBooks) {
        super(name, age, "student");
        this.program = program;
        this.borrowedBooks = borrowedBooks;
    }

    public String getProgram() {
        return this.program;
    }

    public int getBorrowedBooks() {
        return this.borrowedBooks;
    }

    public void setBorrowedBooks(int borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public boolean canBorrow() {
        return borrowedBooks < MAXBORROWS;
    }

    public static void main(String[] args) {
        System.out.println("abc");
    }
}
