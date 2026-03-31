/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package report;



import ADT.ListADT;
import data_management.entity.Book;
import data_management.entity.BookHistory;
import data_management.entity.Penalty;
import data_management.service.BookDataService;
import data_management.service.HistoryRecorder;
import java.time.LocalDate;

public class ReportControl {

    private BookDataService bookService = new BookDataService();

    // ================================
    // 🔥 SUMMARY REPORT
    // ================================
    public void generateSummary() {

        ListADT<Penalty> penalties = HistoryRecorder.getAllPenalties();
        ListADT<BookHistory> histories = HistoryRecorder.getAllBookHistories();

        int borrowedCount = 0;
        double latePenalty = 0;
        double brokenPenalty = 0;

        int lateCount = 0;
        int brokenCount = 0;

        // Count borrowed
        for (int i = 0; i < histories.len(); i++) {
            if (histories.get(i).getAction() == BookHistory.TransactionType.BORROW) {
                borrowedCount++;
            }
        }

        // Penalty calculation
        for (int i = 0; i < penalties.len(); i++) {
            Penalty p = penalties.get(i);

            if (p.getPenaltyType().equalsIgnoreCase("Late Return")) {
                latePenalty += p.getAmount();
                lateCount++;
                System.err.println(lateCount);
            } else if (p.getPenaltyType().equalsIgnoreCase("Broken Book")) {
                brokenPenalty += p.getAmount();
                brokenCount++;
            }
        }

        int total = 0;
        for (int i = 0; i < histories.len(); i++) {
            BookHistory bh = histories.get(i);

            if (bh.getAction() == BookHistory.TransactionType.RETURN) {
                total++;
            }
        }

        double lateRate = (total == 0) ? 0 : (lateCount * 100.0 / total);
        double brokenRate = (total == 0) ? 0 : (brokenCount * 100.0 / total);

        // Available books
        int available = bookService.listAvailableBook().len();

        System.out.println("\n===== SUMMARY =====");
        System.out.println("Borrowed: " + borrowedCount);
        System.out.println("Available: " + available);
        System.out.println("Late Penalty: RM " + latePenalty);
        System.out.println("Broken Penalty: RM " + brokenPenalty);
        System.out.println("Late Rate: " + lateRate + "%");
        System.out.println("Broken Rate: " + brokenRate + "%");
    }

    // ================================
    // 📚 BORROWED BOOKS
    // ================================
    public ListADT<Book> getBorrowedBooks() {
        ListADT<Book> result = new ListADT<>();

        for (int i = 0; i < BookDataService.bookList.len(); i++) {
            Book b = BookDataService.bookList.get(i);
            if (b.isBorrowed()) {
                result.add(b);
            }
        }
        return result;
    }

    // ================================
    // 📚 AVAILABLE BOOKS
    // ================================
    public ListADT<Book> getAvailableBooks() {
        return bookService.listAvailableBook();
    }

    // ================================
    // 📊 6-MONTH COMPARISON
    // ================================
    public void compare6Months() {

        ListADT<BookHistory> histories = HistoryRecorder.getAllBookHistories();

        int[] monthCount = new int[6];
        LocalDate now = LocalDate.now();

        for (int i = 0; i < histories.len(); i++) {

            BookHistory h = histories.get(i);
            int diff = now.getMonthValue() - h.getDate().getMonthValue();

            if (diff >= 0 && diff < 6) {
                monthCount[diff]++;
            }
        }

        System.out.println("\n===== 6 MONTH COMPARISON =====");

        for (int i = 5; i >= 0; i--) {
            System.out.println((i) + " month(s) ago: " + monthCount[i]);
        }

        // Rate calculation
        System.out.println("\n===== RATE CHANGE =====");

        for (int i = 1; i < 6; i++) {
            if (monthCount[i] == 0) continue;

            double rate = ((monthCount[i - 1] - monthCount[i]) * 100.0) / monthCount[i];
            System.out.println("Month " + (i - 1) + " vs " + i + ": " + rate + "%");
        }
    }
}