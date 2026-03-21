package util;

import ADT.ListADT;
import data_management.entity.Staff;
import data_management.entity.Student;
import data_management.entity.UserInfo;
import java.util.Comparator;
import java.util.function.Predicate;

public class UserDisplay extends DisplayTableAction<UserInfo> {

    public UserDisplay(int currentPage, int totalPages) {
        super(currentPage, totalPages);
    }

    @Override
    public String AttributeMenu() {
        System.out.println("\n====== Sort/Search By ======");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Age");
        System.out.println("4. Role");
        System.out.println("5. Program (Student)");
        System.out.println("6. Position (Staff)");
        System.out.println("7. Borrowed Books Count (Student)");
        System.out.println("8. Blacklisted (Student)");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");

        return scanner.nextLine();
    }

    @Override
    public Comparator<UserInfo> getComparatorByOption(String option, boolean ascending) {
        Comparator<UserInfo> comparator;
        switch (option) {
            case "1": comparator = Comparator.comparing(UserInfo::getId); break;
            case "2": comparator = Comparator.comparing(UserInfo::getName, String.CASE_INSENSITIVE_ORDER); break;
            case "3": comparator = Comparator.comparingInt(UserInfo::getAge); break;
            case "4": comparator = Comparator.comparing(UserInfo::getRole); break;
            case "5": comparator = Comparator.comparing(
                u -> (u instanceof Student) ? ((Student) u).getProgram() : "", String.CASE_INSENSITIVE_ORDER); break;
            case "6": comparator = Comparator.comparing(
                u -> (u instanceof Staff) ? ((Staff) u).getPosition() : "", String.CASE_INSENSITIVE_ORDER); break;
            case "7": comparator = Comparator.comparingInt(
                u -> (u instanceof Student) ? ((Student) u).getBorrowedBooks().len() : 0); break;
            case "8": comparator = Comparator.comparing(
                u -> (u instanceof Student) && ((Student) u).isBlackListed()); break;
            default: return null;
        }
        return ascending ? comparator : comparator.reversed();
    }

    @Override
    public Predicate<UserInfo> getPredicateByOption(String option) {
        switch (option) {
            case "1": // ID
                System.out.print("Enter ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                return u -> u.getId() == id;

            case "2": // Name
                System.out.print("Enter Name keyword: ");
                String name = scanner.nextLine();
                return u -> u.getName().toLowerCase().contains(name.toLowerCase());

            case "3": // Age
                System.out.print("Enter Age: ");
                int age = Integer.parseInt(scanner.nextLine());
                return u -> u.getAge() == age;

            case "4": // Role (student/staff)
                System.out.print("Enter Role (student/staff): ");
                String role = scanner.nextLine();
                return u -> u.getRole().equalsIgnoreCase(role);

            case "5": // Program (Student)
                System.out.print("Enter Program keyword: ");
                String program = scanner.nextLine();
                return u -> (u instanceof Student) && ((Student) u).getProgram().equalsIgnoreCase(program);

            case "6": // Position (Staff)
                System.out.print("Enter Position keyword: ");
                String position = scanner.nextLine();
                return u -> (u instanceof Staff) && ((Staff) u).getPosition().equalsIgnoreCase(position);

            case "8": // Blacklisted (Student)
                System.out.print("Search blacklisted students? (true/false): ");
                boolean blacklisted = Boolean.parseBoolean(scanner.nextLine());
                return u -> (u instanceof Student) && ((Student) u).isBlackListed() == blacklisted;

            default:
                return u -> false;
        }
    }

    @Override
    public void tableLayout(int startIndex, int endIndex, ListADT<UserInfo> displayList) {
        System.out.printf("%-5s %-15s %-5s %-10s %-25s %-20s %-15s %-30s%n",
                "ID", "Name", "Age", "Role", "Program/Position", "Borrowed Books", "Blacklisted", "Remark");

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = startIndex; i < endIndex; i++) {
            UserInfo user = displayList.get(i);

            String programOrPosition = "-";
            String borrowedBooks = "-";
            String blacklisted = "-";
            String remark = "-";

            if (user instanceof Student) {
                Student student = (Student) user;
                programOrPosition = student.getProgram();
                borrowedBooks = student.getBorrowedBooks().len() + "";
                blacklisted = student.isBlackListed() ? "Yes" : "No";
                remark = student.getRemark();
            } else if (user instanceof Staff) {
                Staff staff = (Staff) user;
                programOrPosition = staff.getPosition();
            }

            System.out.printf("%-5d %-15s %-5d %-10s %-32s %-13s %-15s %-30s%n",
                    user.getId(),
                    user.getName(),
                    user.getAge(),
                    user.getRole(),
                    programOrPosition,
                    borrowedBooks,
                    blacklisted,
                    remark);
        }
    }
}