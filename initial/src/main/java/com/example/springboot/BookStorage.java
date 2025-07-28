package com.example.springboot;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class BookStorage {
    private Map<Integer, Book> bookShelf;
    
    public BookStorage() {
        this.bookShelf = new HashMap<>();
        // Add some sample books
        bookShelf.put(1, new Book("The Great Gatsby", "F. Scott Fitzgerald", 9780743273565L, 1925, 1));
        bookShelf.put(2, new Book("To Kill a Mockingbird", "Harper Lee", 9780446310789L, 1960, 2));
        bookShelf.put(3, new Book("1984", "George Orwell", 9780451524935L, 1949, 3));
    }
    
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookShelf.values());
    }
    
    public Book getBook(int uuid) {
        return bookShelf.get(uuid);
    }
    
    public void addBook(Book book) {
        bookShelf.put(book.getUUID(), book);
    }
    
    public boolean removeBook(int uuid) {
        return bookShelf.remove(uuid) != null;
    }
}