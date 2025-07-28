package com.example.springboot;

public class Book {
    String title;
    long ISBN;
    int releaseDate;
    int UUID;
    String author;

    public Book(String title, String author, long ISBN, int releaseDate, int UUID) {
        this.title = title;
        this.author = author;
        this.UUID = UUID;
        this.releaseDate = releaseDate;
        this.ISBN = ISBN;
    }
    
    public Book() {     }

     //accessors
    public String getTitle() {
        return title;
    }

    public long getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public int getUUID() {
        return UUID;
    }

    // setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setUUID(int UUID) {
        this.UUID = UUID;
    }
}