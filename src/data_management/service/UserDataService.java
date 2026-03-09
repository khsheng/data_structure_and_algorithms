package data_management.service;

import ADT.ListADT;
import data_management.entity.*;
import java.util.Comparator;
import java.util.function.Predicate;

public class UserDataService implements CrudService<UserInfo>{
    public ListADT<UserInfo> userList;

    public UserDataService()  {
        userList = new ListADT<>();
    }

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
    public void update(int index, String name, int age, String position) {
        if (!(userList.get(index) instanceof Staff)) {
            throw new IllegalArgumentException("User at index " + index + " is not a Staff member.");
        }

        Staff updateUser = (Staff) userList.get(index);

        updateUser.setName(name);
        updateUser.setAge(age);
        updateUser.setPosition(position);

    }

    @Override
    public void update(int index, String name, int age, String program, int borrowedBooks) {
        if (!(userList.get(index) instanceof Student)) {
            throw new IllegalArgumentException("User at index " + index + " is not a Student.");
        }

        Student updateUser = (Student) userList.get(index);

        updateUser.setName(name);
        updateUser.setAge(age);
        updateUser.setProgram(program);
        updateUser.setBorrowedBooks(borrowedBooks);
    }

    @Override
    public ListADT<UserInfo> search(Predicate<UserInfo> parameters) {
        ListADT<Integer> matchedIndex = userList.findAll(parameters);
        ListADT<UserInfo> result = new ListADT<>();
        
        for (int i = 0; i < matchedIndex.len(); i++) {
            result.add(userList.get(matchedIndex.get(i)));
        }

        return result;
    }

    public ListADT<UserInfo> sort(Comparator<UserInfo> comparator) {
        userList.sort(comparator);
        return userList;
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
        UserInfo newUser = new Staff("Alice", 30, "Manager");
        userService.add(newUser);
        userService.add(new Staff("Bob", 25, "Developer"));
        userService.add(new Student("Charlie", 20, "Computer Science", 2));
        userService.add(new Student("David", 22, "Mathematics", 5));
        userService.update(2, "Zharlie", 21, "Software Engineering", 3);
        userService.update(1, "Bob", 26, "Senior Developer");
        System.out.println(userService.search(u -> u.getAge() > 25));

        UserDataService newService = new UserDataService();
        newService.add(userService.search(u -> u.getAge() > 25));
        System.out.println(newService);

        System.out.println(userService);
        userService.sort((a, b) -> b.getName().compareTo(a.getName()));
        System.out.println(userService);
    }
}
