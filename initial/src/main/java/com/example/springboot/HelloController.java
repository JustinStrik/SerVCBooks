package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
public class HelloController {

	private BookStorage bookStorage = new BookStorage();

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/test/")
	public String test() {
		return "Greetings from Spring Boot! testing changes";
	}

	@GetMapping("/books/")
	public String book() {
		return "Welcome to the main page! Working on frontend & input :)";
	}

	@GetMapping("/getbooks/")
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> books = bookStorage.getAllBooks();
		return ResponseEntity.ok(books);
	}

	@GetMapping("/getbook/")
	public ResponseEntity<Book> getBook(@RequestParam int uuid) {
		Book book = bookStorage.getBook(uuid);
		if (book != null) {
			return ResponseEntity.ok(book);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/createbook/")
	public String createBook() {
		return "Create new book";
	}

	@PostMapping("/addbook/")
	public ResponseEntity<String> addBook(@RequestBody Book book) {
		try {
			bookStorage.addBook(book);
			return ResponseEntity.ok("Book added successfully: " + book.getTitle());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error adding book: " + e.getMessage());
		}
	}

	@PostMapping("/removebook/")
	public ResponseEntity<String> removeBook(@RequestParam int uuid) {
		boolean removed = bookStorage.removeBook(uuid);
		if (removed) {
			return ResponseEntity.ok("Book with UUID " + uuid + " removed successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
