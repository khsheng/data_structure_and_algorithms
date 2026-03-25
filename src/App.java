
import Transaction.TransactionMenu;
import data_management.entity.Book;
import data_management.service.BookDataService;
import data_management.service.BorrowBook;
import data_management.service.HistoryRecorder;
import data_management.service.UserDataService;
import java.time.LocalDate;
import util.BookDisplay;
import util.DisplayTableAction;
import util.Testing;


public class App {
    public static void main(String[] args) throws Exception {
        ryanTesting();
        //lipwaiTesting();
        
        //khsTesting();
    }

    public static void lipwaiTesting(){
        TransactionMenu menu = new TransactionMenu();
        menu.start();
    }

    public static void khsTesting(){
        UserDataService userDataService = new UserDataService();
        Testing.addTestUsers(userDataService);

        BookDataService bookDataService = new BookDataService();
        Testing.addTestBooks(bookDataService);

        BorrowBook borrowBook = new BorrowBook();
        boolean success = borrowBook.borrowBook(1, 1) && borrowBook.borrowBook(2, 1);
        System.out.println("Borrowing " + (success ? "successful" : "failed"));

        LocalDate date = LocalDate.of(2024, 2, 6);
        bookDataService.search(b -> b.getId() == 1).get(0).setBorrowedDate(date);

        System.out.println(bookDataService.search(b -> b.getId() == 1).get(0));

        boolean success2 = borrowBook.borrowBook(2, 1);
        System.out.println("Borrowing " + (success2 ? "successful" : "failed"));

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

        DisplayTableAction<Book> displayTable = new BookDisplay(bookDataService.search(b -> true));
        displayTable.displayTable();
    }

    public static void ryanTesting() {

    UserDataService userService = new UserDataService();

  
    util.Testing.addTestUsers(userService);

    login.LoginManager loginManager = new login.LoginManager(userService);
    login.MenuHandler menuHandler = new login.MenuHandler(userService);

    java.util.Scanner sc = new java.util.Scanner(System.in);

    while (true) {

        System.out.println("\n=== LOGIN SYSTEM ===");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        data_management.entity.UserInfo user = loginManager.login(name, id);

        if (user != null) {
            System.out.println("Login successful!");
            System.out.println("Logged in as: " + user.getRole());

            menuHandler.start(user); // go to menu

        } else {
            System.out.println("Login failed.");
        }
    }
}

}





