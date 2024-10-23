public class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    // Constructor
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true; // Default: Book is available when created
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for author
    public String getAuthor() {
        return author;
    }

    // Getter for availability
    public boolean isAvailable() {
        return isAvailable;
    }

    // Setter to change availability
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}
