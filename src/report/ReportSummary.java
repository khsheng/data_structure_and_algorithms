/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package report;

public class ReportSummary {

    private int borrowed;
    private int available;
    private double latePenalty;
    private double brokenPenalty;
    private double lateRate;
    private double brokenRate;

    public void setBorrowed(int v) { borrowed = v; }
    public void setAvailable(int v) { available = v; }
    public void setLatePenalty(double v) { latePenalty = v; }
    public void setBrokenPenalty(double v) { brokenPenalty = v; }
    public void setLateRate(double v) { lateRate = v; }
    public void setBrokenRate(double v) { brokenRate = v; }

    @Override
    public String toString() {
        return "\n=== REPORT SUMMARY ===\n" +
               "Borrowed: " + borrowed +
               "\nAvailable: " + available +
               "\nLate Penalty: RM " + latePenalty +
               "\nBroken Penalty: RM " + brokenPenalty +
               "\nLate Rate: " + lateRate + "%" +
               "\nBroken Rate: " + brokenRate + "%\n";
    }
}