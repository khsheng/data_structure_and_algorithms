package data_management.service;

import ADT.ListADT;
import data_management.entity.*;

public class UserDataService implements CrudService<UserInfo>{
    private ListADT<UserInfo> userList;

    public UserDataService()  {
        userList = new ListADT<>();
    }

    @Override
    public void add(UserInfo user) {
        userList.add(user);
    }

    @Override
    public void remove(int index) {
        userList.remove(index);
    }

    // To do: update method should accept update only for one type of data (chenge Name only) to avoid the bug that when update, the id also will change
    @Override
    public void update(int index, UserInfo user) {
        userList.replace(index, user);
    }

    // To do: This code is generate by AI, and notfunctional, need to be fixed
    @Override
    public ListADT<UserInfo> search(java.util.function.Predicate<UserInfo> predicate) {
        ListADT<UserInfo> result = new ListADT<>();
        for (int i = 0; i < userList.len(); i++) {
            UserInfo user = userList.get(i);
            if (predicate.test(user)) {
                result.add(user);
            }
        }
        return result;
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
        newString.append("      borrowedBooks: ").append(studentMember.getBorrowedBooks()).append(", \n");
        newString.append("      canBorrow: ").append(studentMember.canBorrow()).append(", \n");
        return newString.toString();
    }

    public static void main(String[] args) {
        UserDataService userService = new UserDataService();
        userService.add(new Staff("Alice", 30, "Manager"));
        userService.add(new Staff("Bob", 25, "Developer"));
        userService.add(new Student("Charlie", 20, "Computer Science", 2));
        userService.add(new Student("David", 22, "Mathematics", 5));
        userService.update(1, new Staff("Bob", 26, "Senior Developer"));
        System.out.println(userService);
    }
}
