package data_management.service;

import ADT.ListADT;
import data_management.entity.*;
import java.util.Comparator;
import java.util.function.*;
import util.Testing;
import util.UserDisplay;

public class UserDataService implements CrudService<UserInfo>{
    public static ListADT<UserInfo> userList = new ListADT<>();

    @Override
    public void add(UserInfo user) {
        userList.add(user);
    }

    @Override
    public void add(ListADT<UserInfo> users) {
        for (int i = 0; i < users.len(); i++) {
            userList.add(users.get(i));
        }
    }

    @Override
    public void remove(int index) {
        userList.remove(index);
    }

    @Override
    public void update(int index, Consumer<UserInfo> set) {
        UserInfo updateUser = userList.get(index);
        set.accept(updateUser);
    }

    @Override
    public ListADT<UserInfo> search(Predicate<UserInfo> parameters) {
        ListADT<Integer> matchedIndex = userList.findAll(parameters);
        ListADT<UserInfo> result = new ListADT<>();

        if (matchedIndex.get(0) == -1){
            return result;
        }
        
        for (int i = 0; i < matchedIndex.len(); i++) {
            result.add(userList.get(matchedIndex.get(i)));
        }

        return result;
    }

    public ListADT<UserInfo> sort(Comparator<UserInfo> comparator) {
        userList.sort(comparator);
        return userList;
    }

    public void displayTable() {
        ListADT<UserInfo> copyUserList = userList.copy();

        int pageSize = 5;
        if (copyUserList.len() == 0) {
            System.out.println("No books available.");
            return;
        }

        int currentPage = 1;

        while (true) {
            int totalRecords = copyUserList.len();
            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, totalRecords);
            UserDisplay userDisplay = new UserDisplay(currentPage, totalPages);
            String input;

            userDisplay.tableLayout(startIndex, endIndex, copyUserList);

            // Call action and update currentPage
            input = userDisplay.tableActionMenu();

            switch (input) {
                case "1":
                    currentPage = userDisplay.nextPage();
                    break;

                case "2":
                    currentPage = userDisplay.previousPage();
                    break;

                case "3":
                    String sortBy = userDisplay.AttributeMenu();
                    Boolean ascending = userDisplay.byAscending();

                    Comparator comparator = userDisplay.getComparatorByOption(sortBy, ascending);
                    copyUserList = sort(comparator);
                    break;

                case "4":
                    String searchBy = userDisplay.AttributeMenu();
                    Predicate<UserInfo> predicate = userDisplay.getPredicateByOption(searchBy);

                    copyUserList = search(predicate);
                    break;

                default:
                    return;
            } 

            System.out.println();
        }
    }

    @Override
    public String toString() {
        if (userList.len() == 0) return "[]";

        StringBuilder newString = new StringBuilder();
        newString.append("[\n");
        for (int i = 0; i < userList.len(); i++){
            newString.append("  {\n");

            newString.append("      ID: ").append(userList.get(i).getId()).append(", \n");
            newString.append("      Name: ").append(userList.get(i).getName()).append(", \n");
            newString.append("      Age: ").append(userList.get(i).getAge()).append(", \n");
            newString.append("      Role: ").append(userList.get(i).getRole()).append(", \n");

            if (userList.get(i) instanceof Staff) {
                staffToString(userList.get(i), newString);
            } else if (userList.get(i) instanceof Student) {
                studentToString(userList.get(i), newString);
            }

            newString.append("  }");

            if (i != userList.len() - 1) newString.append(", ");
            newString.append("\n");
        }
        newString.append("]\n");

        return newString.toString();
    }

    public String staffToString(UserInfo staff, StringBuilder newString) {
        Staff staffMember = (Staff) staff;
        newString.append("      Position: ").append(staffMember.getPosition()).append(", \n");
        return newString.toString();
    }

    public String studentToString(UserInfo student, StringBuilder newString) {
        Student studentMember = (Student) student;
        newString.append("      program: ").append(studentMember.getProgram()).append(", \n");

        for (int i = 0; i < studentMember.getBorrowedBooks().len(); i++) {
            newString.append("      Borrowed Book ").append(i + 1).append(": ").append(studentMember.getBorrowedBooks().get(i).getTitle()).append(", \n");
        }

        newString.append("      remainingBorrowLimit: ").append(studentMember.getRemainingBorrowLimit()).append(", \n");
        return newString.toString();
    }


    public static void main(String[] args) {
        BookDataService bookDataService = new BookDataService();
        Testing.addTestBooks(bookDataService);

        UserDataService userDataService = new UserDataService();
        Testing.addTestUsers(userDataService);

        userDataService.displayTable();
    }
}
