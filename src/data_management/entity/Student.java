package data_management.entity;

import ADT.ListADT;

public class Student extends UserInfo {
    private String program;
    private ListADT<Book> borrowedBooks;
    private boolean isBlackListed = false;
    private String remark = "-";
    private static final int MAXBORROWS = 5;

    public Student(String name, int age, String program, String password) {
        super(name, age, "student", password);
        this.program = program;
        this.borrowedBooks = new ListADT<>();
    }

    // Program
    public String getProgram() {
        return this.program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    // Borrowed Books
    public ListADT<Book> getBorrowedBooks() {
        return this.borrowedBooks;
    }

    public boolean canBorrow() {
        if (borrowedBooks.len() >= MAXBORROWS) {
            System.out.println("Borrowing denied: A student cannot borrow more than " + MAXBORROWS + " books.");
            return false;
        } else if (isBlackListed()) {
            System.out.println("Borrowing denied: This student is blacklisted. Reason: " + remark);
            return false;
        }

        return true;
    }


    public int getRemainingBorrowLimit() {
        return MAXBORROWS - borrowedBooks.len();
    }

    public void addBorrowedBooks(Book borrowBook) {
        this.borrowedBooks.add(borrowBook);
    }

    // Blacklist
    public boolean isBlackListed() {
        // Find all indexes of books with penalties
        ListADT<Integer> indexOfLateReturnBook = borrowedBooks.findAll(x -> x.getPenaltyFee() > 0);

        // If no penalties found
        if (indexOfLateReturnBook.len() == 1 && indexOfLateReturnBook.get(0) == -1) {
            this.isBlackListed = false;
            this.remark = "-";
            return false;
        }

        // Otherwise, student is blacklisted
        this.isBlackListed = true;
        StringBuilder sb = new StringBuilder("Student is late returning book ID(s): ");

        for (int i = 0; i < indexOfLateReturnBook.len(); i++) {
            int index = indexOfLateReturnBook.get(i);
            Book book = borrowedBooks.get(index);
            sb.append(book.getId());
            if (i < indexOfLateReturnBook.len() - 1) {
                sb.append(", "); // add commas between IDs
            }
        }

        this.remark = sb.toString();
        return true;
    }

    public String getRemark() {
        return remark;
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %d, Name: %s, Age: %d, Program: %s, Borrowed Books: %d, Blacklisted: %b, Message: %s",
            this.id, this.name, this.age, this.program, this.borrowedBooks.len(), this.isBlackListed, this.remark
        );
    }
}