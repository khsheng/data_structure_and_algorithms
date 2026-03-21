package data_management.service;

import ADT.ListADT;
import data_management.entity.*;
// import data_management.entity.Penalty;
import java.time.LocalDate;

public class HistoryRecorder {
    private static ListADT<Penalty> penaltyList = new ListADT<>();
    private static ListADT<BookHistory> bookHistoryList = new ListADT<>();

    public static void recordPenalty(String penaltyType, Double amount, LocalDate date) {
        Penalty newPenalty = new Penalty(penaltyType, amount, date);
        penaltyList.add(newPenalty);
    }

    public static void recordBookAction(int bookID, int studentID, 
                                        BookHistory.TransactionType action, 
                                        LocalDate date) {
        BookHistory newHistory = new BookHistory(bookID, studentID, action, date);
        bookHistoryList.add(newHistory);
    }

    public static ListADT<Penalty> getAllPenalties() {
        return penaltyList;
    }

    public static ListADT<BookHistory> getAllBookHistories() {
        return bookHistoryList;
    }

    public static void main(String[] args) {
        // Example usage
        recordPenalty("Late Return", 5.0, LocalDate.now());
        recordBookAction(1, 101, BookHistory.TransactionType.BORROW, LocalDate.now());

        System.out.println("All penalties recorded:");
        System.out.println(getAllPenalties());

        System.out.println("All book histories recorded:");
        System.out.println(getAllBookHistories());
    }
}