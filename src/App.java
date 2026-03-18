
import data_management.entity.*;
import data_management.service.*;
import java.time.LocalDate;
import Transaction.TransactionMenu;

public class App {
    public static void main(String[] args) throws Exception {
        TransactionMenu menu = new TransactionMenu();
        menu.start();
        // UserDataService userDataService = new UserDataService();
        // userDataService.add(new Student("Alice", 20, "Computer Science"));
        // userDataService.add(new Staff("Bob", 30, "Librarian"));
        // System.out.println(userDataService);

        // BookDataService bookDataService = new BookDataService();
        // bookDataService.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 10.99, "Fiction"));
        // bookDataService.add(new Book("To Kill a Mockingbird", "Harper Lee", 8.99, "Fiction"));
        // System.out.println(bookDataService);

        // BorrowBook borrowBook = new BorrowBook();
        // boolean success = borrowBook.borrowBook(1, 1) && borrowBook.borrowBook(2, 1);
        // System.out.println("Borrowing " + (success ? "successful" : "failed"));

        // LocalDate date = LocalDate.of(2024, 2, 6);
        // bookDataService.search(b -> b.getId() == 1).get(0).setBorrowedDate(date);

        // System.out.println(bookDataService.search(b -> true).get(0));

        // boolean returnSuccess = borrowBook.returnBook(1, 1, true);
        // System.out.println("Returning " + (returnSuccess ? "successful" : "failed"));
        
        // double PenaltyFee = borrowBook.payingPenalty(1, 1, 1000, true);
        // System.out.println("Change of penalty fee paid: RM" + PenaltyFee);

        // returnSuccess = borrowBook.returnBook(1, 1, true);
        // System.out.println("Returning " + (returnSuccess ? "successful" : "failed"));
        
        // System.out.println(bookDataService.search(b -> true).get(0));
        // System.out.println(bookDataService.search(b -> true).get(1));

    }
}
