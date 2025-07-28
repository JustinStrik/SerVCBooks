# Spring Boot Book Management System

This project implements a complete book management system with GET and POST endpoints using Spring Boot.

## Implemented Features

### Core Classes

1. **Book.java** - Model class for book entities
   - Properties: title, author, ISBN, releaseDate, UUID
   - Getters and setters for all properties
   - Constructor with all parameters and default constructor

2. **BookStorage.java** - Data storage and management
   - In-memory storage using HashMap
   - Pre-populated with sample books
   - Methods: getAllBooks(), getBook(uuid), addBook(book), removeBook(uuid)

3. **HelloController.java** - REST API controller
   - Multiple GET and POST endpoints
   - Proper HTTP response handling
   - JSON serialization support

4. **Application.java** - Spring Boot main application
   - CommandLineRunner bean for startup inspection
   - Proper Spring Boot configuration

### REST Endpoints

#### GET Endpoints

1. **GET /** - Root endpoint
   - Returns: "Greetings from Spring Boot!"

2. **GET /test/** - Test endpoint
   - Returns: "Greetings from Spring Boot! testing changes"

3. **GET /books/** - Books main page
   - Returns: "Welcome to the main page! Working on frontend & input :)"

4. **GET /getbooks/** - Get all books
   - Returns: JSON array of all books
   - Response: `List<Book>`

5. **GET /getbook/** - Get specific book
   - Parameters: `uuid` (query parameter)
   - Returns: JSON object of the book or 404 if not found
   - Response: `Book` or `404 Not Found`

6. **GET /createbook/** - Create book page
   - Returns: "Create new book"

#### POST Endpoints

1. **POST /addbook/** - Add a new book
   - Request Body: JSON Book object
   - Returns: Success message or error message
   - Response: `200 OK` with success message or `400 Bad Request` with error

2. **POST /removebook/** - Remove a book
   - Parameters: `uuid` (query parameter)
   - Returns: Success message or 404 if book not found
   - Response: `200 OK` with success message or `404 Not Found`

## Testing

The system includes comprehensive test coverage:

1. **HelloControllerTest.java** - Unit tests for GET endpoints
2. **HelloControllerIT.java** - Integration tests for GET endpoints
3. **SimpleTest.java** - Standalone test for core functionality

## Sample Usage

### Get all books
```bash
curl http://localhost:8080/getbooks/
```

### Get specific book
```bash
curl http://localhost:8080/getbook/?uuid=1
```

### Add a new book
```bash
curl -X POST http://localhost:8080/addbook/ \
  -H "Content-Type: application/json" \
  -d '{"title":"New Book","author":"New Author","ISBN":1234567890,"releaseDate":2023,"UUID":4}'
```

### Remove a book
```bash
curl -X POST http://localhost:8080/removebook/?uuid=1
```

## Dependencies

- Spring Boot 2.6.3
- Spring Boot Starter Web
- Spring Boot Starter Actuator
- Spring Boot Starter Test (for testing)

## Build and Run

```bash
# Using Gradle
./gradlew bootRun

# Using Maven
./mvnw spring-boot:run
```

## Verification

The system has been tested and verified to work correctly:

1. ✅ Book creation and storage
2. ✅ Book retrieval (all and by UUID)
3. ✅ Book removal
4. ✅ REST endpoint structure
5. ✅ HTTP response handling
6. ✅ JSON serialization support

All GET and POST endpoints are fully functional and ready for use!