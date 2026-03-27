package login;

import java.util.Scanner;
import data_management.entity.*;
import data_management.service.UserDataService;
import util.*;
import ADT.ListADT;

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
            System.out.println("2. Update Info");
            System.out.println("3. Delete Account");
            System.out.println("0. Exit");

            System.out.print("Select Option: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                System.out.println("\n=== YOUR INFORMATION ===");
                displaySingleUser(student);
                break;

                case 2:
                    updateStudentInfo(student);
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
            System.out.println("2. Edit Student Info");
            System.out.println("3. Update Own Info");
            System.out.println("4. Create Staff");
            System.out.println("0. Exit");

            System.out.print("Select Option: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    viewAllUsers();
                    break;

                case 2:
                    editStudentInfo();
                    break;

                case 3:
                    updateStaffInfo(staff);
                    break;

                case 4:
                    createStaff();
                    break;
            }

        } while (choice != 0);
    }

    // ================= COMMON FUNCTIONS =================

    private void updateUser(int id, java.util.function.Consumer<data_management.entity.UserInfo> action) {

            ADT.ListADT<Integer> indexList = UserDataService.userList.findAll(u -> u.getId() == id);

        // not found
            if (indexList.get(0) == -1) {
                System.out.println("User not found.");
            return;
            }

            // update using index
            int index = indexList.get(0);

            userService.update(index, action);

            System.out.println("Updated successfully.");
}

    private void deleteUser(int id) {

        ADT.ListADT<Integer> result = UserDataService.userList.findAll(u -> u.getId() == id);

        // not found
            if (result.get(0) == -1) {
                System.out.println("User not found.");
            return;
            }

        // get index
        int index = result.get(0);

        userService.remove(index);

        System.out.println("Deleted successfully.");
}

    private void createStaff() {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Position: ");
        String position = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Staff staff = new Staff(name, age, position, password);
        userService.add(staff);

        System.out.println("Staff created successfully.");
    }

    private void updateStudentInfo(Student student) {

    int choice;

        do {
            System.out.println("\n=== Current Student Info ===");
            displaySingleUser(student);
            System.out.println("\n--- Update Student Info ---");
            System.out.println("1. Update Name");
            System.out.println("2. Update Age");
            System.out.println("3. Update Program");
            System.out.println("0. Back");

            System.out.print("Select Option: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    updateUser(student.getId(), u -> u.setName(newName));
                    break;

                case 2:
                    System.out.print("Enter new age: ");
                    int newAge = sc.nextInt();
                    sc.nextLine();
                    updateUser(student.getId(), u -> u.setAge(newAge));
                    break;

            case 3:
                    System.out.print("Enter new program: ");
                    String newProgram = sc.nextLine();
                    updateUser(student.getId(), u -> ((Student) u).setProgram(newProgram));
                    break;
            }

        } while (choice != 0);
    }

    private void displaySingleUser(UserInfo user) {
        ListADT<UserInfo> tempList = new ADT.ListADT<>();
        tempList.add(user);

        UserDisplay userDisplay = new UserDisplay(tempList);
        // call your existing table layout
        userDisplay.tableLayout(0, tempList.len(), tempList);
    }


    private void editStudentInfo() {
    int currentPage = 0;
    final int PAGE_SIZE = 10;

    //Get ONLY students
    ADT.ListADT<data_management.entity.UserInfo> allUsers =
            userService.search(u -> u instanceof data_management.entity.Student);

    java.util.Scanner sc = new java.util.Scanner(System.in);

    while (true) {

        int start = currentPage * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, allUsers.len());

        if (start >= allUsers.len()) {
            System.out.println("No more pages.");
            currentPage--;
            continue;
        }

        System.out.println("\n=== STUDENT LIST (Page " + (currentPage + 1) + ") ===");

        UserDisplay userDisplay = new UserDisplay(allUsers);
        userDisplay.tableLayout(start, end, allUsers);
        int totalPages = (int) Math.ceil((double) allUsers.len() / PAGE_SIZE);
        System.out.println("Page " + (currentPage + 1) + " / " + totalPages);

        System.out.println("\n1. Next Page");
        System.out.println("2. Previous Page");
        System.out.println("3. Select Student by ID");
        System.out.println("0. Back");

        System.out.print("Select Option: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                currentPage++;
                break;

            case 2:
                if (currentPage > 0) currentPage--;
                break;

            case 3:
                System.out.print("Enter Student ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                data_management.entity.UserInfo selected = findUserById(id);

                if (selected != null && selected instanceof data_management.entity.Student) {
                    updateStudentInfo((data_management.entity.Student) selected);
                } else {
                    System.out.println("Student not found.");
                }
                break;

            case 0:
                return;
        }
    }
}

    private data_management.entity.UserInfo findUserById(int id) {

    ADT.ListADT<Integer> result =
        UserDataService.userList.findAll(u -> u.getId() == id);

        // check if not found
        if (result.get(0) == -1) {
            return null;
     }

            // get first matched index
        int index = result.get(0);

        return UserDataService.userList.get(index);
}


    private void updateStaffInfo(data_management.entity.Staff staff) {
    int choice;
    do {
        System.out.println("\n=== Current Staff Info ===");
        displaySingleUser(staff);

        System.out.println("\n--- Update Staff Info ---");
        System.out.println("1. Update Name");
        System.out.println("2. Update Age");
        System.out.println("3. Update Position");
        System.out.println("0. Back");

        System.out.print("Select Option: ");
        choice = sc.nextInt();
        sc.nextLine(); 

        switch (choice) {
            case 1:
                System.out.print("New Name: ");
                String name = sc.nextLine();
                updateUser(staff.getId(), u -> u.setName(name));
                break;

            case 2:
                System.out.print("New Age: ");
                int age = sc.nextInt();
                sc.nextLine();
                updateUser(staff.getId(), u -> u.setAge(age));
                break;

            case 3:
                System.out.print("New Position: ");
                String pos = sc.nextLine();
                updateUser(staff.getId(), u -> ((data_management.entity.Staff) u).setPosition(pos));
                break;
        }

    } while (choice != 0);
}

private void viewAllUsers() {

    ADT.ListADT<UserInfo> allUsers =
        userService.search(u -> true);

    DisplayTableAction<UserInfo> table =
        new UserDisplay(allUsers);

    table.displayTable(); 
}
}