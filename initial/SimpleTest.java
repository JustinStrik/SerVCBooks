import com.example.springboot.Book;
import com.example.springboot.BookStorage;
import java.util.List;

public class SimpleTest {
    public static void main(String[] args) {
        System.out.println("Testing Book Management System");
        
        // Test Book creation
        Book book1 = new Book("Test Book", "Test Author", 1234567890L, 2023, 1);
        System.out.println("Created book: " + book1.getTitle() + " by " + book1.getAuthor());
        
        // Test BookStorage
        BookStorage storage = new BookStorage();
        System.out.println("Initial books count: " + storage.getAllBooks().size());
        
        // Test adding a book
        storage.addBook(book1);
        System.out.println("After adding book, count: " + storage.getAllBooks().size());
        
        // Test getting a book
        Book retrievedBook = storage.getBook(1);
        if (retrievedBook != null) {
            System.out.println("Retrieved book: " + retrievedBook.getTitle());
        }
        
        // Test removing a book
        boolean removed = storage.removeBook(1);
        System.out.println("Book removed: " + removed);
        System.out.println("Final books count: " + storage.getAllBooks().size());
        
        System.out.println("All tests completed successfully!");
    }
}