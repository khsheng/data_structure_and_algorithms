/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package report;



import ADT.ListADT;
import data_management.entity.Book;
import java.util.Scanner;
import util.BookDisplay;
import util.DisplayTableAction;

public class ReportUI {

    private ReportControl control = new ReportControl();
    private Scanner sc = new Scanner(System.in);

    public void start() {

        while (true) {
            System.out.println("\n===== REPORT MENU =====");
            System.out.println("1. Summary");
            System.out.println("2. Borrowed Book");
            System.out.println("3. Available Book");
            System.out.println("4. Monthly Book Borrowed");
            System.out.println("0. Exit");

            System.out.print("Select Option: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    control.generateSummary();
                    break;

                case 2:
                    showBorrowed();
                    break;

                case 3:
                    showAvailable();
                    break;

                case 4:
                    control.compare6Months();
                    break;

                case 0:
                    return;
            }
        }
    }

    // ============================
    // 📚 DISPLAY BORROWED
    // ============================
    private void showBorrowed() {
        ListADT<Book> list = control.getBorrowedBooks();

        DisplayTableAction<Book> table = new BookDisplay(list);
        table.displayTable();
    }

    // ============================
    // 📚 DISPLAY AVAILABLE
    // ============================
    private void showAvailable() {
        ListADT<Book> list = control.getAvailableBooks();

        DisplayTableAction<Book> table = new BookDisplay(list);
        table.displayTable();
    }

    // ============================
    // MAIN METHOD
    // ============================
    public static void main(String[] args) {
        new ReportUI().start();
    }
}