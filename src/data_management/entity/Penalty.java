package data_management.entity;

import java.time.LocalDate;

public class Penalty {
    private String penalty_type;
    private double amount;
    private LocalDate date;

    public Penalty(String penalty_type, double amount, LocalDate date) {
        this.penalty_type = penalty_type;
        this.amount = amount;
        this.date = date;
    }

    public String getPenaltyType() {
        return this.penalty_type;
    }

    public double getAmount() {
        return this.amount;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return String.format("Penalty Type: %s, Amount: %.2f, Date: %s", penalty_type, amount, date);
    }

}
