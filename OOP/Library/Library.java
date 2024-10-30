// Library.java
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("Member added: " + member);
    }

    public void borrowBook(String bookId, String memberId) {
        Book book = findBook(bookId);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("Book " + book.getTitle() + " borrowed by member " + memberId);
        } else {
            System.out.println("Book is not available or does not exist.");
        }
    }

    public void returnBook(String bookId) {
        Book book = findBook(bookId);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println("Book " + book.getTitle() + " returned.");
        } else {
            System.out.println("Book is not borrowed or does not exist.");
        }
    }

    private Book findBook(String bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }
}
