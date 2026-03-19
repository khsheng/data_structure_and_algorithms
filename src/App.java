
import Transaction.TransactionMenu;
import data_management.entity.Book;
import data_management.entity.Staff;
import data_management.entity.Student;
import data_management.entity.UserInfo;
import data_management.service.BookDataService;
import data_management.service.BorrowBook;
import data_management.service.HistoryRecorder;
import data_management.service.UserDataService;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws Exception {
        lipwaiTesting();
        
        khsTesting();
    }

    public static void lipwaiTesting(){
        TransactionMenu menu = new TransactionMenu();
        menu.start();
    }

    public static void khsTesting(){
        UserDataService userDataService = new UserDataService();
        UserInfo user1 = new Student("Alice", 20, "Computer Science");
        UserInfo user2 = new Staff("Bob", 30, "Librarian");
        userDataService.add(user1);
        userDataService.add(user2);
        System.out.println(userDataService);

        BookDataService bookDataService = new BookDataService();
        bookDataService.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 10.99, "Fiction"));
        bookDataService.add(new Book("To Kill a Mockingbird", "Harper Lee", 8.99, "Fiction"));
        System.out.println(bookDataService);

        BorrowBook borrowBook = new BorrowBook();
        boolean success = borrowBook.borrowBook(1, 1) && borrowBook.borrowBook(2, 1);
        System.out.println("Borrowing " + (success ? "successful" : "failed"));

        LocalDate date = LocalDate.of(2024, 2, 6);
        bookDataService.search(b -> b.getId() == 1).get(0).setBorrowedDate(date);

        System.out.println(bookDataService.search(b -> true).get(0));

        boolean returnSuccess = borrowBook.returnBook(1, 1, true);
        System.out.println("Returning " + (returnSuccess ? "successful" : "failed"));
        
        double PenaltyFee = borrowBook.payingPenalty(1, 1, 1000, true);
        System.out.println("Change of penalty fee paid: RM" + PenaltyFee);

        returnSuccess = borrowBook.returnBook(1, 1, true);
        System.out.println("Returning " + (returnSuccess ? "successful" : "failed"));
        
        System.out.println(bookDataService.search(b -> true).get(0));
        System.out.println(bookDataService.search(b -> true).get(1));


        System.out.println("All penalties recorded:");
        System.out.println(HistoryRecorder.getAllPenalties());

        System.out.println("All book histories recorded:");
        System.out.println(HistoryRecorder.getAllBookHistories());
    }


}
