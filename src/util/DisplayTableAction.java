package util;

import java.util.Comparator;
import java.util.Scanner;
import java.util.function.*;

import data_management.entity.Book;

public abstract class DisplayTableAction {
    int currentPage;
    int totalPages;
    Scanner scanner = new Scanner(System.in);

    public DisplayTableAction(int currentPage, int totalPages){
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public String tableActionMenu(){
        // Footer with page info
        System.out.println("Page " + currentPage + " of " + totalPages);

        // Ask user what to do next
        System.out.println("\n=== Table Navigation Menu ===");
        System.out.println("Page " + currentPage + " of " + totalPages);
        System.out.println("1. Next Page");
        System.out.println("2. Previous Page");
        System.out.println("3. Sort By");
        System.out.println("4. Search By");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");

        String input = scanner.nextLine();

        return input;
    }

    public int nextPage(){
        if (currentPage < totalPages) {
            currentPage++;
        } else {
            System.out.println("Already at last page.");
        }

        return currentPage;
    }

    public int previousPage(){
        if (currentPage > 1) {
            currentPage--;
        } else {
            System.out.println("Already at first page.");
        }

        return currentPage;
    }

    public boolean byAscending(){
        System.out.print("Sort ascending (A) or descending (D): ");
        String order = scanner.nextLine();
        boolean ascending = order.equalsIgnoreCase("A");

        return ascending;
    }

    public abstract String AttributeMenu();

    public abstract Comparator<Book> getComparatorByOption(String option, boolean ascending);

    public abstract Predicate<Book> getPredicateByOption(String option);
}
