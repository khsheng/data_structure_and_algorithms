package util;

import ADT.ListADT;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.*;

public abstract class DisplayTableAction<T> {
    int currentPage = 1;
    int totalPages;
    ListADT<T> displayList;
    Scanner scanner = new Scanner(System.in);

    public DisplayTableAction(ListADT<T> list){
        displayList = list.copy();
    }

    public void displayTable() {
        int pageSize = 5;
        if (displayList.len() == 0) {
            System.out.println("No books available.");
            return;
        }

        while (true) {
            int totalRecords = displayList.len();
            totalPages = (int) Math.ceil((double) totalRecords / pageSize);
            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, totalRecords);
            String input;

            tableLayout(startIndex, endIndex, displayList);

            // Call action and update currentPage
            input = tableActionMenu();

            switch (input) {
                case "1":
                    currentPage = nextPage();
                    break;

                case "2":
                    currentPage = previousPage();
                    break;

                case "3":
                    String sortBy = AttributeMenu();
                    Boolean ascending = byAscending();

                    Comparator comparator = getComparatorByOption(sortBy, ascending);
                    displayList = sort(comparator);
                    break;

                case "4":
                    String searchBy = AttributeMenu();
                    Predicate<T> predicate = getPredicateByOption(searchBy);

                    displayList = search(predicate);
                    break;

                default:
                    return;
            } 

            System.out.println();
        }
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

    public abstract Comparator<T> getComparatorByOption(String option, boolean ascending);

    public abstract Predicate<T> getPredicateByOption(String option);

    public abstract void tableLayout(int startIndex, int endIndex, ListADT<T> displayList);

    public abstract ListADT<T> sort (Comparator<T> comparator);

    public abstract ListADT<T> search (Predicate<T> parameter);
}