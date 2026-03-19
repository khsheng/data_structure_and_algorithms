package data_management.service;

import ADT.ListADT;
import data_management.entity.BookHistory;
import data_management.entity.Penalty;
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
}