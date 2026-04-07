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
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

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
        System.out.printf("Late Penalty: RM %.2f\n", latePenalty);
        System.out.printf("Broken Penalty: RM %.2f\n", brokenPenalty);
        System.out.printf("Late Rate: %.2f%%\n", lateRate);
        System.out.printf("Broken Rate: %.2f%%\n", brokenRate);
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
        YearMonth now = YearMonth.now();

        for (int i = 0; i < histories.len(); i++) {

            BookHistory h = histories.get(i);
            YearMonth historyMonth = YearMonth.from(h.getDate());

            int diff = (int) java.time.temporal.ChronoUnit.MONTHS.between(historyMonth, now);

            if (diff >= 0 && diff < 6) {
                monthCount[diff]++;
            }
        }

        System.out.println("\n===== Monthly Book Borrowed (Last 6 Months) =====");

        for (int i = 0; i < 6; i++) {
            YearMonth targetMonth = now.minusMonths(i);

            String monthName = targetMonth.getMonth()
                    .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

            System.out.println(monthName + ": " + monthCount[i]);
        }

        
        System.out.println("\n===== MONTH-TO-MONTH CHANGE =====");

        for (int i = 1; i < 6; i++) {

            int newer = monthCount[i - 1];
            int older = monthCount[i];

            YearMonth m1 = now.minusMonths(i - 1);
            YearMonth m2 = now.minusMonths(i);

            String m1Name = m1.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            String m2Name = m2.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

            if (older == 0) {
                if (newer == 0) {
                    System.out.println(m1Name + " vs " + m2Name + ": 0.00%");
                } else {
                    System.out.println(m1Name + " vs " + m2Name + ": ∞ (no data in previous month)");
                }
                continue;
            }

            double rate = ((newer - older) * 100.0) / older;

            System.out.println(m1Name + " vs " + m2Name + ": " + String.format("%.2f", rate) + "%");
        }
    }
}