package util;

import data_management.entity.Book;
import data_management.entity.Staff;
import data_management.entity.Student;
import data_management.entity.UserInfo;
import data_management.service.BookDataService;
import data_management.service.UserDataService;

public class Testing {
    public static void addTestUsers(UserDataService userDataService) {
        UserInfo user1  = new Student("Alice", 20, "Computer Science", "alice123");
        UserInfo user2  = new Staff("Bob", 30, "Librarian", "bobPass");
        UserInfo user3  = new Student("Charlie", 22, "Information Technology", "charlieIT");
        UserInfo user4  = new Staff("Diana", 28, "Assistant Librarian", "dianaLib");
        UserInfo user5  = new Student("Ethan", 19, "Software Engineering", "ethanSE");
        UserInfo user6  = new Staff("Fiona", 35, "Library Manager", "fionaMgr");
        UserInfo user7  = new Student("George", 21, "Data Science", "georgeDS");
        UserInfo user8  = new Staff("Hannah", 40, "Archivist", "hannahArc");
        UserInfo user9  = new Student("Isabella", 23, "Artificial Intelligence", "isabellaAI");
        UserInfo user10 = new Staff("Jack", 33, "Library Technician", "jackTech");

        // Add them all to the service
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
