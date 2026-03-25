package login;

import java.util.Scanner;
import data_management.entity.*;
import data_management.service.UserDataService;
import login.UserCSVLoader;

public class MenuHandler {

    private UserDataService userService;
    private Scanner sc = new Scanner(System.in);

    public MenuHandler(UserDataService userService) {
        this.userService = userService;
    }

    public void start(UserInfo user) {
        if (user instanceof Staff) {
            staffMenu((Staff) user);
        } else if (user instanceof Student) {
            studentMenu((Student) user);
        }
    }

    // ================= STUDENT =================
    private void studentMenu(Student student) {
        int choice;

        do {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. View Info");
            System.out.println("2. Update Name");
            System.out.println("3. Delete Account");
            System.out.println("0. Exit");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(student);
                    break;

                case 2:
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    updateUser(student.getId(), newName);
                    break;

                case 3:
                    deleteUser(student.getId());
                    System.out.println("Account deleted.");
                    return;
            }

        } while (choice != 0);
    }

    // ================= STAFF =================
    private void staffMenu(Staff staff) {
        int choice;

        do {
            System.out.println("\n--- Staff Menu ---");
            System.out.println("1. View All Users");
            System.out.println("2. Update Student Name");
            System.out.println("3. Delete Student");
            System.out.println("4. Create Student");
            System.out.println("0. Exit");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(userService);
                    break;

                case 2:
                    System.out.print("Enter student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new name: ");
                    String name = sc.nextLine();

                    updateUser(id, name);
                    break;

                case 3:
                    System.out.print("Enter student ID: ");
                    int deleteId = sc.nextInt();
                    deleteUser(deleteId);
                    break;

                case 4:
                    createStudent();
                    break;
            }

        } while (choice != 0);
    }

    // ================= COMMON FUNCTIONS =================

    private void updateUser(int id, String newName) {
        for (int i = 0; i < UserDataService.userList.len(); i++) {
            if (UserDataService.userList.get(i).getId() == id) {
                userService.update(i, u -> u.setName(newName));
                UserCSVLoader.saveAll("user.csv", userService);
                System.out.println("Updated successfully.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    private void deleteUser(int id) {
        for (int i = 0; i < UserDataService.userList.len(); i++) {
            if (UserDataService.userList.get(i).getId() == id) {
                userService.remove(i);
                UserCSVLoader.saveAll("user.csv", userService);
                System.out.println("Deleted successfully.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    private void createStudent() {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Program: ");
        String program = sc.nextLine();

        Student student = new Student(name, age, program);
        userService.add(student);
        UserCSVLoader.saveAll("user.csv", userService);

        System.out.println("Student created.");
    }
}