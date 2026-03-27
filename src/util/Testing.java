package util;

import data_management.entity.Book;
import data_management.entity.Staff;
import data_management.entity.Student;
import data_management.entity.UserInfo;
import data_management.service.BookDataService;
import data_management.service.UserDataService;

public class Testing {
    public static void addTestUsers(UserDataService userDataService) {
        UserInfo user1 = new Student("ryan123", "Ryan", 22, "Computer Science", "123");
        UserInfo user2 = new Staff("admin1", "Admin Bob", 30, "Librarian", "123");
        UserInfo user3 = new Student("charlie123", "Charlie", 22, "Information Technology", "123");
        UserInfo user4 = new Staff("diana1", "Diana", 28, "Assistant Librarian", "admin");
        UserInfo user5 = new Student("ethan123", "Ethan", 19, "Software Engineering", "123");
        UserInfo user6 = new Staff("fiona1", "Fiona", 35, "Library Manager", "admin");
        UserInfo user7 = new Student("george123", "George", 21, "Data Science", "123");
        UserInfo user8 = new Staff("hannah1", "Hannah", 40, "Archivist", "admin");
        UserInfo user9 = new Student("isabella123", "Isabella", 23, "Artificial Intelligence", "123");
        UserInfo user10 = new Staff("jack1", "Jack", 33, "Library Technician", "admin");

        UserInfo user11 = new Student("khs123", "Khs", 20, "Cyber Security", "123");
        UserInfo user12 = new Student("liam123", "Liam", 21, "Software Engineering", "123");
        UserInfo user13 = new Student("mia123", "Mia", 22, "Data Science", "123");
        UserInfo user14 = new Student("noah123", "Noah", 19, "Computer Science", "123");
        UserInfo user15 = new Student("olivia123", "Olivia", 23, "Information Systems", "123");
        UserInfo user16 = new Student("paul123", "Paul", 20, "Artificial Intelligence", "123");
        UserInfo user17 = new Student("quinn123", "Quinn", 21, "Cyber Security", "123");
        UserInfo user18 = new Student("alice123", "Alice", 22, "Software Engineering", "123");
        UserInfo user19 = new Student("sophia123", "Sophia", 19, "Data Science", "123");
        UserInfo user20 = new Student("thomas123", "Thomas", 24, "Computer Science", "123");

        UserInfo user21 = new Student("uma123", "Uma", 20, "Information Technology", "123");
        UserInfo user22 = new Student("victor123", "Victor", 21, "Software Engineering", "123");
        UserInfo user23 = new Student("wendy123", "Wendy", 22, "Artificial Intelligence", "123");
        UserInfo user24 = new Student("xavier123", "Xavier", 23, "Cyber Security", "123");
        UserInfo user25 = new Student("yara123", "Yara", 20, "Computer Science", "123");
        UserInfo user26 = new Student("zack123", "Zack", 21, "Data Science", "123");
        UserInfo user27 = new Student("aaron123", "Aaron", 22, "Software Engineering", "123");
        UserInfo user28 = new Student("bella123", "Bella", 19, "Information Systems", "123");
        UserInfo user29 = new Student("cody123", "Cody", 23, "Cyber Security", "123");
        UserInfo user30 = new Student("daisy123", "Daisy", 20, "Artificial Intelligence", "123");

        UserInfo user31 = new Student("eli123", "Eli", 21, "Computer Science", "123");
        UserInfo user32 = new Student("faith123", "Faith", 22, "Software Engineering", "123");
        UserInfo user33 = new Student("gavin123", "Gavin", 23, "Data Science", "123");
        UserInfo user34 = new Student("hazel123", "Hazel", 19, "Cyber Security", "123");
        UserInfo user35 = new Student("ivan123", "Ivan", 20, "Information Technology", "123");
        UserInfo user36 = new Student("julia123", "Julia", 21, "Artificial Intelligence", "123");
        UserInfo user37 = new Student("kyle123", "Kyle", 22, "Computer Science", "123");
        UserInfo user38 = new Student("luna123", "Luna", 23, "Software Engineering", "123");
        UserInfo user39 = new Student("mason123", "Mason", 20, "Data Science", "123");
        UserInfo user40 = new Student("nina123", "Nina", 21, "Cyber Security", "123");

        UserInfo user41 = new Staff("oscar1", "Oscar", 36, "Senior Librarian", "admin");
        UserInfo user42 = new Staff("penelope1", "Penelope", 38, "HR Manager", "admin");
        UserInfo user43 = new Staff("quincy1", "Quincy", 41, "System Administrator", "admin");
        UserInfo user44 = new Staff("rachel1", "Rachel", 37, "Library Officer", "admin");
        UserInfo user45 = new Staff("steven1", "Steven", 39, "IT Support", "admin");
        UserInfo user46 = new Staff("tina1", "Tina", 42, "Operations Manager", "admin");
        UserInfo user47 = new Staff("admin", "Super Admin", 42, "Operations Manager", "admin");

        UserInfo user48 = new Student("owen123", "Owen", 20, "Software Engineering", "123");
        UserInfo user49 = new Student("penny123", "Penny", 21, "Data Science", "123");
        UserInfo user50 = new Student("sam123", "Sam", 22, "Cyber Security", "123");





// ===== ADD ALL USERS =====
        userDataService.add(user1);
        userDataService.add(user2);
        userDataService.add(user3);
        userDataService.add(user4);
        userDataService.add(user5);
        userDataService.add(user6);
        userDataService.add(user7);
        userDataService.add(user8);
        userDataService.add(user9);
        userDataService.add(user10);

        userDataService.add(user11);
        userDataService.add(user12);
        userDataService.add(user13);
        userDataService.add(user14);
        userDataService.add(user15);
        userDataService.add(user16);
        userDataService.add(user17);
        userDataService.add(user18);
        userDataService.add(user19);
        userDataService.add(user20);

        userDataService.add(user21);
        userDataService.add(user22);
        userDataService.add(user23);
        userDataService.add(user24);
        userDataService.add(user25);
        userDataService.add(user26);
        userDataService.add(user27);
        userDataService.add(user28);
        userDataService.add(user29);
        userDataService.add(user30);

        userDataService.add(user31);
        userDataService.add(user32);
        userDataService.add(user33);
        userDataService.add(user34);
        userDataService.add(user35);
        userDataService.add(user36);
        userDataService.add(user37);
        userDataService.add(user38);
        userDataService.add(user39);
        userDataService.add(user40);

        userDataService.add(user41);
        userDataService.add(user42);
        userDataService.add(user43);
        userDataService.add(user44);
        userDataService.add(user45);
        userDataService.add(user46);

        userDataService.add(user47);
        userDataService.add(user48);
        userDataService.add(user49);
        userDataService.add(user50);


        // Print out the service contents
        System.out.println(userDataService);
    }

    public static void addTestBooks(BookDataService bookDataService) {
        bookDataService.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 10.99, "Fiction"));
        bookDataService.add(new Book("To Kill a Mockingbird", "Harper Lee", 8.99, "Fiction"));
        bookDataService.add(new Book("1984", "George Orwell", 9.99, "Dystopian"));
        bookDataService.add(new Book("Pride and Prejudice", "Jane Austen", 7.99, "Romance"));
        bookDataService.add(new Book("Moby-Dick", "Herman Melville", 11.50, "Adventure"));
        bookDataService.add(new Book("The Catcher in the Rye", "J.D. Salinger", 8.50, "Fiction"));
        bookDataService.add(new Book("Brave New World", "Aldous Huxley", 9.50, "Science Fiction"));
        bookDataService.add(new Book("The Hobbit", "J.R.R. Tolkien", 12.99, "Fantasy"));
        bookDataService.add(new Book("Crime and Punishment", "Fyodor Dostoevsky", 10.50, "Classic"));
        bookDataService.add(new Book("War and Peace", "Leo Tolstoy", 14.99, "Historical"));

        // Print out the service contents
        System.out.println(bookDataService);
    }

    public static void main(String[] args) {
        System.out.println("ad");
    }
}
