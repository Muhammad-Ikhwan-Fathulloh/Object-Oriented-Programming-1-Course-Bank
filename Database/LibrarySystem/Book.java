public class Book extends LibraryEntity {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author, boolean isAvailable) {
        super(id);
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String getDetails() {
        return "Book [ID=" + getId() + ", Title=" + title + ", Author=" + author + ", Available=" + isAvailable + "]";
    }
}