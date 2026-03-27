package util;

import data_management.entity.Book;
import data_management.entity.Staff;
import data_management.entity.Student;
import data_management.entity.UserInfo;
import data_management.service.BookDataService;
import data_management.service.UserDataService;

public class Testing {
    public static void addTestUsers(UserDataService userDataService) {
        UserInfo user1 = new Student("Ryan", 22, "Computer Science", "123");
        UserInfo user2 = new Staff("admin", 30, "Librarian", "123");
        UserInfo user3 = new Student("Charlie", 22, "Information Technology", "123");
        UserInfo user4 = new Staff("Diana", 28, "Assistant Librarian", "admin");
        UserInfo user5 = new Student("Ethan", 19, "Software Engineering", "123");
        UserInfo user6 = new Staff("Fiona", 35, "Library Manager", "admin");
        UserInfo user7 = new Student("George", 21, "Data Science", "123");
        UserInfo user8 = new Staff("Hannah", 40, "Archivist", "admin");
        UserInfo user9 = new Student("Isabella", 23, "Artificial Intelligence", "123");
        UserInfo user10 = new Staff("Jack", 33, "Library Technician", "admin");

// ===== ADD MORE STUDENTS (40 total students) =====
        UserInfo user11 = new Student("Khs", 20, "Cyber Security", "123");
        UserInfo user12 = new Student("Liam", 21, "Software Engineering", "123");
        UserInfo user13 = new Student("Mia", 22, "Data Science", "123");
        UserInfo user14 = new Student("Noah", 19, "Computer Science", "123");
        UserInfo user15 = new Student("Olivia", 23, "Information Systems", "123");
        UserInfo user16 = new Student("Paul", 20, "Artificial Intelligence", "123");
        UserInfo user17 = new Student("Quinn", 21, "Cyber Security", "123");
        UserInfo user18 = new Student("Alice", 22, "Software Engineering", "123");
        UserInfo user19 = new Student("Sophia", 19, "Data Science", "123");
        UserInfo user20 = new Student("Thomas", 24, "Computer Science", "123");

        UserInfo user21 = new Student("Uma", 20, "Information Technology", "123");
        UserInfo user22 = new Student("Victor", 21, "Software Engineering", "123");
        UserInfo user23 = new Student("Wendy", 22, "Artificial Intelligence", "123");
        UserInfo user24 = new Student("Xavier", 23, "Cyber Security", "123");
        UserInfo user25 = new Student("Yara", 20, "Computer Science", "123");
        UserInfo user26 = new Student("Zack", 21, "Data Science", "123");
        UserInfo user27 = new Student("Aaron", 22, "Software Engineering", "123");
        UserInfo user28 = new Student("Bella", 19, "Information Systems", "123");
        UserInfo user29 = new Student("Cody", 23, "Cyber Security", "123");
        UserInfo user30 = new Student("Daisy", 20, "Artificial Intelligence", "123");

        UserInfo user31 = new Student("Eli", 21, "Computer Science", "123");
        UserInfo user32 = new Student("Faith", 22, "Software Engineering", "123");
        UserInfo user33 = new Student("Gavin", 23, "Data Science", "123");
        UserInfo user34 = new Student("Hazel", 19, "Cyber Security", "123");
        UserInfo user35 = new Student("Ivan", 20, "Information Technology", "123 ");
        UserInfo user36 = new Student("Julia", 21, "Artificial Intelligence", "123");
        UserInfo user37 = new Student("Kyle", 22, "Computer Science", "123");
        UserInfo user38 = new Student("Luna", 23, "Software Engineering", "123");
        UserInfo user39 = new Student("Mason", 20, "Data Science", "123");
        UserInfo user40 = new Student("Nina", 21, "Cyber Security", "123");

// ===== ADD REMAINING STAFF (total staff = 10) =====
        UserInfo user41 = new Staff("Oscar", 36, "Senior Librarian", "admin");
        UserInfo user42 = new Staff("Penelope", 38, "HR Manager", "admin");
        UserInfo user43 = new Staff("Quincy", 41, "System Administrator", "admin");
        UserInfo user44 = new Staff("Rachel", 37, "Library Officer", "admin");
        UserInfo user45 = new Staff("Steven", 39, "IT Support", "admin");
        UserInfo user46 = new Staff("Tina", 42, "Operations Manager","admin");
        UserInfo user47 = new Staff("admin", 42, "Operations Manager", "admin");
        UserInfo user48 = new Student("Owen", 20, "Software Engineering", "123");
        UserInfo user49 = new Student("Penny", 21, "Data Science", "123");
        UserInfo user50 = new Student("Sam", 22, "Cyber Security", "123");





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
        bookDataService.add(new Book("The Silent Observatory", "Maeve Callahan", 14.99, "Science Fiction"));
        bookDataService.add(new Book("Clockwork Isles", "Rafael Bennett", 18.50, "Fantasy"));
        bookDataService.add(new Book("Harbor of Glass", "Ivy Moreau", 12.75, "Mystery"));
        bookDataService.add(new Book("Algorithm of Dreams", "Noah Carpenter", 21.00, "Technology"));
        bookDataService.add(new Book("Letters to Tomorrow", "Sienna Duarte", 16.25, "Fiction"));
        bookDataService.add(new Book("Midnight Botanist", "Leo Chambers", 11.99, "Fantasy"));
        bookDataService.add(new Book("Atlas of Broken Maps", "Amir Solano", 19.45, "Adventure"));
        bookDataService.add(new Book("Velvet Rebellion", "Tessa Calder", 13.10, "Historical Fiction"));
        bookDataService.add(new Book("The Painted Oath", "Jules Mercer", 15.80, "Romance"));
        bookDataService.add(new Book("Cathedral of Fog", "Harper Lin", 17.95, "Horror"));
        bookDataService.add(new Book("Cobalt Paradox", "Xavier Holt", 22.40, "Science Fiction"));
        bookDataService.add(new Book("The Last Orchard", "Mara Ellison", 10.75, "Drama"));
        bookDataService.add(new Book("Frequency Keepers", "Caleb Ortiz", 18.20, "Thriller"));
        bookDataService.add(new Book("Embers on Water", "Lucia Navarro", 14.60, "Poetry"));
        bookDataService.add(new Book("The Sapphire Cartographer", "Elias Porter", 23.15, "Fantasy"));
        bookDataService.add(new Book("Neon Harvest", "Aria Patel", 13.50, "Dystopian"));
        bookDataService.add(new Book("Marble Republic", "Dorian Vega", 24.80, "Political Fiction"));
        bookDataService.add(new Book("The Paper Helmsman", "Isla Monroe", 12.30, "Children"));
        bookDataService.add(new Book("Gravity's Orphans", "Holden Briggs", 20.99, "Science Fiction"));
        bookDataService.add(new Book("The Lantern Villas", "Penelope Shaw", 11.45, "Mystery"));
        bookDataService.add(new Book("Spiral of Sand", "Gwen Rivera", 16.75, "Adventure"));
        bookDataService.add(new Book("Firefly Diplomats", "Owen Caldwell", 19.30, "Fantasy"));
        bookDataService.add(new Book("The Jade Treaty", "Mina Clarke", 17.10, "Historical"));
        bookDataService.add(new Book("Patchwork Constellations", "Elliot Ramsey", 12.85, "Romance"));
        bookDataService.add(new Book("Signal at Dawn", "Theo Kline", 15.20, "Thriller"));
        bookDataService.add(new Book("The Hollow Conservatory", "Nadia Winters", 18.95, "Gothic"));
        bookDataService.add(new Book("Rivers Without Names", "Emerson Pike", 13.40, "Literary Fiction"));
        bookDataService.add(new Book("Stone & Starship", "Remy Laurent", 22.10, "Science Fiction"));
        bookDataService.add(new Book("The Clockmaker's Niece", "Brielle Sutton", 11.60, "Steampunk"));
        bookDataService.add(new Book("Harvest of Comets", "Kiran Desai", 24.25, "Space Opera"));
        bookDataService.add(new Book("Inkbound Pilgrims", "Talia Rios", 14.05, "Fantasy"));
        bookDataService.add(new Book("The Coral Citadel", "Jasper Flynn", 12.95, "Adventure"));
        bookDataService.add(new Book("Echoes in Amber", "Rowan Calder", 19.10, "Mystery"));
        bookDataService.add(new Book("Cartwheels on Orion", "Mikayla Frost", 21.35, "Science Fiction"));
        bookDataService.add(new Book("The Umbrella Archivist", "Silas Becker", 13.25, "Fiction"));
        bookDataService.add(new Book("Citizens of Rust", "Valerie Kade", 16.95, "Dystopian"));
        bookDataService.add(new Book("The Orchard Astronomer", "Daphne Quinn", 15.60, "Middle Grade"));
        bookDataService.add(new Book("Magnetism for Poets", "Gideon Park", 11.05, "Non-Fiction"));
        bookDataService.add(new Book("The Glass Desert Train", "Wren Atherton", 18.55, "Adventure"));
        bookDataService.add(new Book("Chasing Northlights", "Elena Morozov", 17.75, "Travel"));
        bookDataService.add(new Book("Lanternwork Guild", "Quincy Hale", 13.95, "Fantasy"));
        bookDataService.add(new Book("Threads of Meridian", "Sabine Adler", 22.75, "Historical Fantasy"));
        bookDataService.add(new Book("Pixel Saints", "Bryce Holloway", 12.10, "Cyberpunk"));
        bookDataService.add(new Book("The Borrowed Lighthouse", "Natalie Comer", 16.45, "Drama"));
        bookDataService.add(new Book("Oracle of the Salt Road", "Priya Nader", 19.70, "Mythology"));
        bookDataService.add(new Book("The Velvet Physicist", "Anders Kim", 23.90, "Science Fiction"));
        bookDataService.add(new Book("Canyons of Paper", "Helena Brooks", 14.30, "Essay"));
        bookDataService.add(new Book("Moonlit Apiary", "Farah Idris", 12.65, "Nature"));
        bookDataService.add(new Book("The Fifth Gallery", "Simon Leclerc", 18.15, "Art"));
        bookDataService.add(new Book("Starlit Archivum", "Ophelia Grant", 21.90, "Fantasy"));


        // Print out the service contents
        System.out.println(bookDataService);
    }

    public static void main(String[] args) {
        System.out.println("ad");
    }
}
