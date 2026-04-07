
import data_management.entity.UserInfo;
import data_management.service.BookDataService;
import data_management.service.UserDataService;
import report.ReportUI;
import util.SampleData;



public class Main {
    public static void main(String[] args) throws Exception {
        // Add sample data
        UserDataService userService = new UserDataService();
        SampleData.addTestUsers(userService);

        BookDataService bookDataService = new BookDataService();
        SampleData.addTestBooks(bookDataService);
        SampleData.addTestBorrowedBooks(bookDataService);

        SampleData.addTestHistoryData();
        SampleData.addTestPenaltiesRecord();

        
        
        librarySystem();
        new ReportUI().start();
    }

    public static void librarySystem() {
        UserDataService userService = new UserDataService();

        login.LoginManager loginManager = new login.LoginManager(userService);
        login.MenuHandler menuHandler = new login.MenuHandler(userService);

        java.util.Scanner sc = new java.util.Scanner(System.in);

        while (true) {

            System.out.println("\n=== LOGIN SYSTEM ===");
            System.out.println("1. Login");
            System.out.println("2. Register (Student)");
            System.out.println("0. Exit");

            System.out.print("Select Option: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    // ✅ LOGIN
                    System.out.print("Enter Username: ");
                    String userName = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    UserInfo user = loginManager.login(userName, password);

                    if (user != null) {
                        System.out.println("Login successful!");
                        System.out.println("Logged in as: " + user.getRole());

                        menuHandler.start(user); // go to menu
                    } else {
                        System.out.println("Login failed.");
                    }
                    break;

                case 2:
                    // ✅ REGISTER STUDENT
                    menuHandler.registerStudent(sc);
                    break;

                case 0:
                    System.out.println("Exiting system...");
                    return;
            }
        }
    }

}





