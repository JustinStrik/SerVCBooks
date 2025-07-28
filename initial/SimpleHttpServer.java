import com.example.springboot.*;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class SimpleHttpServer {
    private static final int PORT = 8080;
    private static HelloControllerSimulator controller = new HelloControllerSimulator();
    
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Simple HTTP Server started on port " + PORT);
            System.out.println("Test the endpoints:");
            System.out.println("  GET  http://localhost:8080/");
            System.out.println("  GET  http://localhost:8080/test/");
            System.out.println("  GET  http://localhost:8080/books/");
            System.out.println("  GET  http://localhost:8080/getbooks/");
            System.out.println("  GET  http://localhost:8080/getbook/?uuid=1");
            System.out.println("  GET  http://localhost:8080/createbook/");
            System.out.println("  POST http://localhost:8080/addbook/ (with JSON body)");
            System.out.println("  POST http://localhost:8080/removebook/?uuid=1");
            System.out.println("\nPress Ctrl+C to stop the server\n");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleRequest(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }
    
    private static void handleRequest(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            
            String requestLine = in.readLine();
            if (requestLine == null) return;
            
            String[] parts = requestLine.split(" ");
            String method = parts[0];
            String path = parts[1];
            
            System.out.println("Received: " + method + " " + path);
            
            // Parse query parameters
            Map<String, String> queryParams = new HashMap<>();
            if (path.contains("?")) {
                String[] pathParts = path.split("\\?", 2);
                path = pathParts[0];
                String query = pathParts[1];
                for (String param : query.split("&")) {
                    String[] keyValue = param.split("=", 2);
                    if (keyValue.length == 2) {
                        queryParams.put(keyValue[0], keyValue[1]);
                    }
                }
            }
            
            String response = "";
            String contentType = "text/plain";
            
            // Handle GET requests
            if ("GET".equals(method)) {
                switch (path) {
                    case "/":
                        response = controller.index();
                        break;
                    case "/test/":
                        response = controller.test();
                        break;
                    case "/books/":
                        response = controller.book();
                        break;
                    case "/getbooks/":
                        List<Book> books = controller.getBooks();
                        response = booksToJson(books);
                        contentType = "application/json";
                        break;
                    case "/getbook/":
                        String uuidStr = queryParams.get("uuid");
                        if (uuidStr != null) {
                            try {
                                int uuid = Integer.parseInt(uuidStr);
                                Book book = controller.getBook(uuid);
                                if (book != null) {
                                    response = bookToJson(book);
                                    contentType = "application/json";
                                } else {
                                    response = "Book not found";
                                }
                            } catch (NumberFormatException e) {
                                response = "Invalid UUID format";
                            }
                        } else {
                            response = "UUID parameter required";
                        }
                        break;
                    case "/createbook/":
                        response = controller.createBook();
                        break;
                    default:
                        response = "404 Not Found";
                        break;
                }
            }
            // Handle POST requests
            else if ("POST".equals(method)) {
                switch (path) {
                    case "/addbook/":
                        // Read request body
                        StringBuilder body = new StringBuilder();
                        String line;
                        while ((line = in.readLine()) != null && !line.isEmpty()) {
                            body.append(line);
                        }
                        // For simplicity, we'll just return a success message
                        response = "Book added successfully (simulated)";
                        break;
                    case "/removebook/":
                        String uuidStr = queryParams.get("uuid");
                        if (uuidStr != null) {
                            try {
                                int uuid = Integer.parseInt(uuidStr);
                                response = controller.removeBook(uuid);
                            } catch (NumberFormatException e) {
                                response = "Invalid UUID format";
                            }
                        } else {
                            response = "UUID parameter required";
                        }
                        break;
                    default:
                        response = "404 Not Found";
                        break;
                }
            }
            
            // Send response
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: " + contentType + "; charset=UTF-8");
            out.println("Content-Length: " + response.getBytes("UTF-8").length);
            out.println();
            out.println(response);
            
        } catch (IOException e) {
            System.err.println("Error handling request: " + e.getMessage());
        }
    }
    
    private static String booksToJson(List<Book> books) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < books.size(); i++) {
            if (i > 0) json.append(",");
            json.append(bookToJson(books.get(i)));
        }
        json.append("]");
        return json.toString();
    }
    
    private static String bookToJson(Book book) {
        return String.format(
            "{\"title\":\"%s\",\"author\":\"%s\",\"ISBN\":%d,\"releaseDate\":%d,\"UUID\":%d}",
            book.getTitle(), book.getAuthor(), book.getISBN(), book.getReleaseDate(), book.getUUID()
        );
    }
}