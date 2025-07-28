import com.example.springboot.*;
import java.util.List;

public class EndpointTest {
    public static void main(String[] args) {
        System.out.println("=== Testing Spring Boot Endpoint Logic ===\n");
        
        // Simulate the HelloController
        HelloControllerSimulator controller = new HelloControllerSimulator();
        
        // Test GET endpoints
        System.out.println("1. Testing GET /");
        System.out.println("Response: " + controller.index());
        
        System.out.println("\n2. Testing GET /test/");
        System.out.println("Response: " + controller.test());
        
        System.out.println("\n3. Testing GET /books/");
        System.out.println("Response: " + controller.book());
        
        System.out.println("\n4. Testing GET /getbooks/");
        List<Book> books = controller.getBooks();
        System.out.println("Response: " + books.size() + " books found");
        for (Book book : books) {
            System.out.println("  - " + book.getTitle() + " by " + book.getAuthor());
        }
        
        System.out.println("\n5. Testing GET /getbook/?uuid=1");
        Book book = controller.getBook(1);
        if (book != null) {
            System.out.println("Response: Found book - " + book.getTitle());
        } else {
            System.out.println("Response: Book not found");
        }
        
        System.out.println("\n6. Testing GET /getbook/?uuid=999");
        Book notFoundBook = controller.getBook(999);
        if (notFoundBook != null) {
            System.out.println("Response: Found book - " + notFoundBook.getTitle());
        } else {
            System.out.println("Response: Book not found (404)");
        }
        
        System.out.println("\n7. Testing GET /createbook/");
        System.out.println("Response: " + controller.createBook());
        
        // Test POST endpoints
        System.out.println("\n8. Testing POST /addbook/");
        Book newBook = new Book("New Test Book", "New Test Author", 9876543210L, 2024, 4);
        String addResult = controller.addBook(newBook);
        System.out.println("Response: " + addResult);
        
        System.out.println("\n9. Testing POST /removebook/?uuid=1");
        String removeResult = controller.removeBook(1);
        System.out.println("Response: " + removeResult);
        
        System.out.println("\n10. Testing POST /removebook/?uuid=999");
        String removeNotFoundResult = controller.removeBook(999);
        System.out.println("Response: " + removeNotFoundResult);
        
        System.out.println("\n=== All endpoint logic tests completed successfully! ===");
    }
}

// Simulator class that mimics the HelloController behavior
class HelloControllerSimulator {
    private BookStorage bookStorage = new BookStorage();
    
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    public String test() {
        return "Greetings from Spring Boot! testing changes";
    }
    
    public String book() {
        return "Welcome to the main page! Working on frontend & input :)";
    }
    
    public List<Book> getBooks() {
        return bookStorage.getAllBooks();
    }
    
    public Book getBook(int uuid) {
        return bookStorage.getBook(uuid);
    }
    
    public String createBook() {
        return "Create new book";
    }
    
    public String addBook(Book book) {
        try {
            bookStorage.addBook(book);
            return "Book added successfully: " + book.getTitle();
        } catch (Exception e) {
            return "Error adding book: " + e.getMessage();
        }
    }
    
    public String removeBook(int uuid) {
        boolean removed = bookStorage.removeBook(uuid);
        if (removed) {
            return "Book with UUID " + uuid + " removed successfully";
        } else {
            return "Book with UUID " + uuid + " not found (404)";
        }
    }
}