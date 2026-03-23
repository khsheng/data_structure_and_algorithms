package sample_data;

import ADT.ListADT;
import data_management.entity.Book;
import data_management.service.BookDataService;

import java.util.HashSet;
import java.util.Set;

/**
 * Provides a deterministic catalogue of sample books so interactive flows can
 * rely on consistent fixtures.
 */
public final class book_data {
    private static final BookSeed[] BOOKS = new BookSeed[] {
        new BookSeed("The Silent Observatory", "Maeve Callahan", 14.99, "Science Fiction"),
        new BookSeed("Clockwork Isles", "Rafael Bennett", 18.50, "Fantasy"),
        new BookSeed("Harbor of Glass", "Ivy Moreau", 12.75, "Mystery"),
        new BookSeed("Algorithm of Dreams", "Noah Carpenter", 21.00, "Technology"),
        new BookSeed("Letters to Tomorrow", "Sienna Duarte", 16.25, "Fiction"),
        new BookSeed("Midnight Botanist", "Leo Chambers", 11.99, "Fantasy"),
        new BookSeed("Atlas of Broken Maps", "Amir Solano", 19.45, "Adventure"),
        new BookSeed("Velvet Rebellion", "Tessa Calder", 13.10, "Historical Fiction"),
        new BookSeed("The Painted Oath", "Jules Mercer", 15.80, "Romance"),
        new BookSeed("Cathedral of Fog", "Harper Lin", 17.95, "Horror"),
        new BookSeed("Cobalt Paradox", "Xavier Holt", 22.40, "Science Fiction"),
        new BookSeed("The Last Orchard", "Mara Ellison", 10.75, "Drama"),
        new BookSeed("Frequency Keepers", "Caleb Ortiz", 18.20, "Thriller"),
        new BookSeed("Embers on Water", "Lucia Navarro", 14.60, "Poetry"),
        new BookSeed("The Sapphire Cartographer", "Elias Porter", 23.15, "Fantasy"),
        new BookSeed("Neon Harvest", "Aria Patel", 13.50, "Dystopian"),
        new BookSeed("Marble Republic", "Dorian Vega", 24.80, "Political Fiction"),
        new BookSeed("The Paper Helmsman", "Isla Monroe", 12.30, "Children"),
        new BookSeed("Gravity's Orphans", "Holden Briggs", 20.99, "Science Fiction"),
        new BookSeed("The Lantern Villas", "Penelope Shaw", 11.45, "Mystery"),
        new BookSeed("Spiral of Sand", "Gwen Rivera", 16.75, "Adventure"),
        new BookSeed("Firefly Diplomats", "Owen Caldwell", 19.30, "Fantasy"),
        new BookSeed("The Jade Treaty", "Mina Clarke", 17.10, "Historical"),
        new BookSeed("Patchwork Constellations", "Elliot Ramsey", 12.85, "Romance"),
        new BookSeed("Signal at Dawn", "Theo Kline", 15.20, "Thriller"),
        new BookSeed("The Hollow Conservatory", "Nadia Winters", 18.95, "Gothic"),
        new BookSeed("Rivers Without Names", "Emerson Pike", 13.40, "Literary Fiction"),
        new BookSeed("Stone & Starship", "Remy Laurent", 22.10, "Science Fiction"),
        new BookSeed("The Clockmaker's Niece", "Brielle Sutton", 11.60, "Steampunk"),
        new BookSeed("Harvest of Comets", "Kiran Desai", 24.25, "Space Opera"),
        new BookSeed("Inkbound Pilgrims", "Talia Rios", 14.05, "Fantasy"),
        new BookSeed("The Coral Citadel", "Jasper Flynn", 12.95, "Adventure"),
        new BookSeed("Echoes in Amber", "Rowan Calder", 19.10, "Mystery"),
        new BookSeed("Cartwheels on Orion", "Mikayla Frost", 21.35, "Science Fiction"),
        new BookSeed("The Umbrella Archivist", "Silas Becker", 13.25, "Fiction"),
        new BookSeed("Citizens of Rust", "Valerie Kade", 16.95, "Dystopian"),
        new BookSeed("The Orchard Astronomer", "Daphne Quinn", 15.60, "Middle Grade"),
        new BookSeed("Magnetism for Poets", "Gideon Park", 11.05, "Non-Fiction"),
        new BookSeed("The Glass Desert Train", "Wren Atherton", 18.55, "Adventure"),
        new BookSeed("Chasing Northlights", "Elena Morozov", 17.75, "Travel"),
        new BookSeed("Lanternwork Guild", "Quincy Hale", 13.95, "Fantasy"),
        new BookSeed("Threads of Meridian", "Sabine Adler", 22.75, "Historical Fantasy"),
        new BookSeed("Pixel Saints", "Bryce Holloway", 12.10, "Cyberpunk"),
        new BookSeed("The Borrowed Lighthouse", "Natalie Comer", 16.45, "Drama"),
        new BookSeed("Oracle of the Salt Road", "Priya Nader", 19.70, "Mythology"),
        new BookSeed("The Velvet Physicist", "Anders Kim", 23.90, "Science Fiction"),
        new BookSeed("Canyons of Paper", "Helena Brooks", 14.30, "Essay"),
        new BookSeed("Moonlit Apiary", "Farah Idris", 12.65, "Nature"),
        new BookSeed("The Fifth Gallery", "Simon Leclerc", 18.15, "Art"),
        new BookSeed("Starlit Archivum", "Ophelia Grant", 21.90, "Fantasy")
    };

    private book_data() {
        // Utility class
    }

    public static void seed(BookDataService bookDataService) {
        seed(bookDataService, BOOKS.length);
    }

    public static void seed(BookDataService bookDataService, int targetCount) {
        int currentCount = BookDataService.bookList.len();
        if (currentCount >= targetCount) {
            return;
        }

        Set<String> existingTitles = new HashSet<>();
        for (int i = 0; i < BookDataService.bookList.len(); i++) {
            existingTitles.add(BookDataService.bookList.get(i).getTitle());
        }

        ListADT<Book> booksToAdd = new ListADT<>();
        for (BookSeed seed : BOOKS) {
            if (currentCount + booksToAdd.len() >= targetCount) {
                break;
            }

            if (existingTitles.add(seed.getTitle())) {
                booksToAdd.add(seed.toBook());
            }
        }

        bookDataService.add(booksToAdd);
    }

    private static final class BookSeed {
        private final String title;
        private final String author;
        private final double price;
        private final String category;

        private BookSeed(String title, String author, double price, String category) {
            this.title = title;
            this.author = author;
            this.price = price;
            this.category = category;
        }

        private String getTitle() {
            return title;
        }

        private Book toBook() {
            return new Book(title, author, price, category);
        }
    }
}
