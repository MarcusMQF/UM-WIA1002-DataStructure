import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A library management system that demonstrates the use of Bag ADT
 * for managing books, borrowers, and library operations.
 * 
 * @author Data Structure Course
 * @version 1.0
 */
public class LibrarySystem {
    
    /**
     * Represents a book in the library system.
     */
    public static class Book {
        private String isbn;
        private String title;
        private String author;
        private String genre;
        private int publicationYear;
        private boolean isAvailable;
        
        public Book(String isbn, String title, String author, String genre, int year) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.publicationYear = year;
            this.isAvailable = true;
        }
        
        // Getters
        public String getIsbn() { return isbn; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public String getGenre() { return genre; }
        public int getPublicationYear() { return publicationYear; }
        public boolean isAvailable() { return isAvailable; }
        
        // Setters
        public void setAvailable(boolean available) { this.isAvailable = available; }
        
        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Book book = (Book) other;
            return isbn.equals(book.isbn);
        }
        
        @Override
        public String toString() {
            return String.format("\"%s\" by %s (%d) [%s] - %s", 
                title, author, publicationYear, genre, 
                isAvailable ? "Available" : "Borrowed");
        }
    }
    
    /**
     * Represents a library member/borrower.
     */
    public static class Member {
        private String memberId;
        private String name;
        private String email;
        private LocalDate joinDate;
        
        public Member(String memberId, String name, String email) {
            this.memberId = memberId;
            this.name = name;
            this.email = email;
            this.joinDate = LocalDate.now();
        }
        
        public String getMemberId() { return memberId; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public LocalDate getJoinDate() { return joinDate; }
        
        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Member member = (Member) other;
            return memberId.equals(member.memberId);
        }
        
        @Override
        public String toString() {
            return String.format("%s (ID: %s) - %s", name, memberId, email);
        }
    }
    
    /**
     * Represents a borrowing record.
     */
    public static class BorrowRecord {
        private Book book;
        private Member member;
        private LocalDate borrowDate;
        private LocalDate dueDate;
        
        public BorrowRecord(Book book, Member member) {
            this.book = book;
            this.member = member;
            this.borrowDate = LocalDate.now();
            this.dueDate = borrowDate.plusWeeks(2); // 2-week loan period
        }
        
        public Book getBook() { return book; }
        public Member getMember() { return member; }
        public LocalDate getBorrowDate() { return borrowDate; }
        public LocalDate getDueDate() { return dueDate; }
        
        public boolean isOverdue() {
            return LocalDate.now().isAfter(dueDate);
        }
        
        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            BorrowRecord record = (BorrowRecord) other;
            return book.equals(record.book) && member.equals(record.member);
        }
        
        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return String.format("%s borrowed by %s (Due: %s)%s", 
                book.getTitle(), member.getName(), 
                dueDate.format(formatter),
                isOverdue() ? " - OVERDUE" : "");
        }
    }
    
    /**
     * Main library class using bags for different collections.
     */
    public static class Library {
        private BagInterface<Book> bookCollection;
        private BagInterface<Member> members;
        private BagInterface<BorrowRecord> borrowRecords;
        private String libraryName;
        
        public Library(String name) {
            this.libraryName = name;
            this.bookCollection = new LinkedBag<>();
            this.members = new LinkedBag<>();
            this.borrowRecords = new LinkedBag<>();
        }
        
        /**
         * Adds a book to the library collection.
         */
        public boolean addBook(Book book) {
            return bookCollection.add(book);
        }
        
        /**
         * Registers a new member.
         */
        public boolean registerMember(Member member) {
            if (members.contains(member)) {
                System.out.println("Member already registered: " + member.getName());
                return false;
            }
            return members.add(member);
        }
        
        /**
         * Borrows a book to a member.
         */
        public boolean borrowBook(String isbn, String memberId) {
            Book bookToBorrow = findBookByIsbn(isbn);
            Member borrower = findMemberById(memberId);
            
            if (bookToBorrow == null) {
                System.out.println("Book not found with ISBN: " + isbn);
                return false;
            }
            
            if (borrower == null) {
                System.out.println("Member not found with ID: " + memberId);
                return false;
            }
            
            if (!bookToBorrow.isAvailable()) {
                System.out.println("Book is currently borrowed: " + bookToBorrow.getTitle());
                return false;
            }
            
            bookToBorrow.setAvailable(false);
            BorrowRecord record = new BorrowRecord(bookToBorrow, borrower);
            borrowRecords.add(record);
            
            System.out.println("Book borrowed successfully: " + bookToBorrow.getTitle() + 
                             " by " + borrower.getName());
            return true;
        }
        
        /**
         * Returns a borrowed book.
         */
        public boolean returnBook(String isbn, String memberId) {
            Book bookToReturn = findBookByIsbn(isbn);
            Member returner = findMemberById(memberId);
            
            if (bookToReturn == null || returner == null) {
                System.out.println("Book or member not found.");
                return false;
            }
            
            BorrowRecord recordToRemove = null;
            BorrowRecord[] records = borrowRecords.toArray();
            
            for (BorrowRecord record : records) {
                if (record.getBook().equals(bookToReturn) && 
                    record.getMember().equals(returner)) {
                    recordToRemove = record;
                    break;
                }
            }
            
            if (recordToRemove == null) {
                System.out.println("No borrow record found for this book and member.");
                return false;
            }
            
            bookToReturn.setAvailable(true);
            borrowRecords.remove(recordToRemove);
            
            System.out.println("Book returned successfully: " + bookToReturn.getTitle());
            return true;
        }
        
        /**
         * Finds a book by ISBN.
         */
        private Book findBookByIsbn(String isbn) {
            Book[] books = bookCollection.toArray();
            for (Book book : books) {
                if (book.getIsbn().equals(isbn)) {
                    return book;
                }
            }
            return null;
        }
        
        /**
         * Finds a member by ID.
         */
        private Member findMemberById(String memberId) {
            Member[] memberArray = members.toArray();
            for (Member member : memberArray) {
                if (member.getMemberId().equals(memberId)) {
                    return member;
                }
            }
            return null;
        }
        
        /**
         * Gets all books by a specific author.
         */
        public BagInterface<Book> getBooksByAuthor(String author) {
            BagInterface<Book> authorBooks = new LinkedBag<>();
            Book[] books = bookCollection.toArray();
            
            for (Book book : books) {
                if (book.getAuthor().equalsIgnoreCase(author)) {
                    authorBooks.add(book);
                }
            }
            
            return authorBooks;
        }
        
        /**
         * Gets all books in a specific genre.
         */
        public BagInterface<Book> getBooksByGenre(String genre) {
            BagInterface<Book> genreBooks = new LinkedBag<>();
            Book[] books = bookCollection.toArray();
            
            for (Book book : books) {
                if (book.getGenre().equalsIgnoreCase(genre)) {
                    genreBooks.add(book);
                }
            }
            
            return genreBooks;
        }
        
        /**
         * Gets all available books.
         */
        public BagInterface<Book> getAvailableBooks() {
            BagInterface<Book> availableBooks = new LinkedBag<>();
            Book[] books = bookCollection.toArray();
            
            for (Book book : books) {
                if (book.isAvailable()) {
                    availableBooks.add(book);
                }
            }
            
            return availableBooks;
        }
        
        /**
         * Gets all borrowed books.
         */
        public BagInterface<Book> getBorrowedBooks() {
            BagInterface<Book> borrowedBooks = new LinkedBag<>();
            Book[] books = bookCollection.toArray();
            
            for (Book book : books) {
                if (!book.isAvailable()) {
                    borrowedBooks.add(book);
                }
            }
            
            return borrowedBooks;
        }
        
        /**
         * Gets overdue books.
         */
        public BagInterface<BorrowRecord> getOverdueRecords() {
            BagInterface<BorrowRecord> overdueRecords = new LinkedBag<>();
            BorrowRecord[] records = borrowRecords.toArray();
            
            for (BorrowRecord record : records) {
                if (record.isOverdue()) {
                    overdueRecords.add(record);
                }
            }
            
            return overdueRecords;
        }
        
        /**
         * Generates library statistics.
         */
        public String getLibraryStats() {
            StringBuilder stats = new StringBuilder();
            stats.append("=== ").append(libraryName).append(" Statistics ===\n");
            stats.append("Total Books: ").append(bookCollection.getCurrentSize()).append("\n");
            stats.append("Available Books: ").append(getAvailableBooks().getCurrentSize()).append("\n");
            stats.append("Borrowed Books: ").append(getBorrowedBooks().getCurrentSize()).append("\n");
            stats.append("Total Members: ").append(members.getCurrentSize()).append("\n");
            stats.append("Active Loans: ").append(borrowRecords.getCurrentSize()).append("\n");
            stats.append("Overdue Items: ").append(getOverdueRecords().getCurrentSize()).append("\n");
            
            return stats.toString();
        }
        
        /**
         * Displays all books in the collection.
         */
        public String displayAllBooks() {
            StringBuilder display = new StringBuilder();
            display.append("=== All Books in ").append(libraryName).append(" ===\n");
            
            Book[] books = bookCollection.toArray();
            for (Book book : books) {
                display.append(book.toString()).append("\n");
            }
            
            return display.toString();
        }
        
        /**
         * Displays all members.
         */
        public String displayAllMembers() {
            StringBuilder display = new StringBuilder();
            display.append("=== Library Members ===\n");
            
            Member[] memberArray = members.toArray();
            for (Member member : memberArray) {
                display.append(member.toString()).append("\n");
            }
            
            return display.toString();
        }
        
        /**
         * Displays current borrow records.
         */
        public String displayBorrowRecords() {
            StringBuilder display = new StringBuilder();
            display.append("=== Current Borrow Records ===\n");
            
            if (borrowRecords.isEmpty()) {
                display.append("No books currently borrowed.\n");
            } else {
                BorrowRecord[] records = borrowRecords.toArray();
                for (BorrowRecord record : records) {
                    display.append(record.toString()).append("\n");
                }
            }
            
            return display.toString();
        }
    }
    
    /**
     * Demo method showing library system operations.
     */
    public static void main(String[] args) {
        // Create library
        Library library = new Library("University Central Library");
        
        System.out.println("=== Library Management System Demo ===\n");
        
        // Add books to the library
        library.addBook(new Book("978-0262033848", "Introduction to Algorithms", "Thomas Cormen", "Computer Science", 2009));
        library.addBook(new Book("978-0321573513", "Algorithms", "Robert Sedgewick", "Computer Science", 2011));
        library.addBook(new Book("978-0073523408", "Data Structures and Algorithm Analysis", "Mark Weiss", "Computer Science", 2011));
        library.addBook(new Book("978-0134685991", "Effective Java", "Joshua Bloch", "Programming", 2017));
        library.addBook(new Book("978-0596007126", "Head First Design Patterns", "Eric Freeman", "Programming", 2004));
        library.addBook(new Book("978-0451524935", "1984", "George Orwell", "Fiction", 1949));
        library.addBook(new Book("978-0062315007", "The Alchemist", "Paulo Coelho", "Fiction", 1988));
        
        // Register members
        library.registerMember(new Member("M001", "Alice Johnson", "alice@university.edu"));
        library.registerMember(new Member("M002", "Bob Smith", "bob@university.edu"));
        library.registerMember(new Member("M003", "Carol Davis", "carol@university.edu"));
        library.registerMember(new Member("M004", "David Wilson", "david@university.edu"));
        
        // Display initial library state
        System.out.println(library.getLibraryStats());
        System.out.println(library.displayAllBooks());
        System.out.println(library.displayAllMembers());
        
        // Demonstrate borrowing
        System.out.println("=== Borrowing Books ===");
        library.borrowBook("978-0262033848", "M001"); // Alice borrows Algorithms book
        library.borrowBook("978-0134685991", "M002"); // Bob borrows Effective Java
        library.borrowBook("978-0451524935", "M003"); // Carol borrows 1984
        library.borrowBook("978-0062315007", "M001"); // Alice borrows The Alchemist
        
        // Try to borrow an already borrowed book
        library.borrowBook("978-0262033848", "M004"); // Should fail
        
        System.out.println("\n" + library.displayBorrowRecords());
        
        // Show books by genre
        System.out.println("=== Computer Science Books ===");
        BagInterface<Book> csBooks = library.getBooksByGenre("Computer Science");
        System.out.println(BagOperations.bagToString(csBooks));
        
        System.out.println("\n=== Programming Books ===");
        BagInterface<Book> progBooks = library.getBooksByGenre("Programming");
        System.out.println(BagOperations.bagToString(progBooks));
        
        // Show available vs borrowed books
        System.out.println("\n=== Available Books ===");
        BagInterface<Book> availableBooks = library.getAvailableBooks();
        System.out.println("Count: " + availableBooks.getCurrentSize());
        System.out.println(BagOperations.bagToString(availableBooks));
        
        System.out.println("\n=== Borrowed Books ===");
        BagInterface<Book> borrowedBooks = library.getBorrowedBooks();
        System.out.println("Count: " + borrowedBooks.getCurrentSize());
        System.out.println(BagOperations.bagToString(borrowedBooks));
        
        // Return some books
        System.out.println("\n=== Returning Books ===");
        library.returnBook("978-0134685991", "M002"); // Bob returns Effective Java
        library.returnBook("978-0451524935", "M003"); // Carol returns 1984
        
        // Updated library statistics
        System.out.println("\n=== Updated Library Statistics ===");
        System.out.println(library.getLibraryStats());
        System.out.println(library.displayBorrowRecords());
        
        // Show books by author
        System.out.println("=== Books by Robert Sedgewick ===");
        BagInterface<Book> sedgewickBooks = library.getBooksByAuthor("Robert Sedgewick");
        System.out.println(BagOperations.bagToString(sedgewickBooks));
        
        System.out.println("\n=== Library Operations Complete ===");
    }
} 