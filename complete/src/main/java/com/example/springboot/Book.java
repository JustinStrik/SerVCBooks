import java.time.chrono.IsoChronology;

class Book {
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

    
}