import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem implements LibraryOperations {
    private Connection connection;

    public LibrarySystem() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public void addBook(Book book) {
        String query = "INSERT INTO books (title, author, is_available) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setBoolean(3, book.isAvailable());
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void viewBooks() {
        String query = "SELECT * FROM books";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("is_available")
                ));
            }

            books.forEach(book -> System.out.println(book.getDetails()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void borrowBook(int bookId, int userId) {
        String checkAvailability = "SELECT is_available FROM books WHERE id = ?";
        String borrowBook = "INSERT INTO transactions (book_id, user_id) VALUES (?, ?)";
        String updateBook = "UPDATE books SET is_available = FALSE WHERE id = ?";

        try (PreparedStatement checkStmt = connection.prepareStatement(checkAvailability);
             PreparedStatement borrowStmt = connection.prepareStatement(borrowBook);
             PreparedStatement updateStmt = connection.prepareStatement(updateBook)) {

            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getBoolean("is_available")) {
                borrowStmt.setInt(1, bookId);
                borrowStmt.setInt(2, userId);
                borrowStmt.executeUpdate();

                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();

                System.out.println("Book borrowed successfully!");
            } else {
                System.out.println("Book is not available!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnBook(int bookId) {
        String query = "UPDATE books SET is_available = TRUE WHERE id = ?";
        String updateTransaction = "UPDATE transactions SET return_date = CURRENT_TIMESTAMP WHERE book_id = ? AND return_date IS NULL";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             PreparedStatement transStmt = connection.prepareStatement(updateTransaction)) {

            transStmt.setInt(1, bookId);
            transStmt.executeUpdate();

            stmt.setInt(1, bookId);
            stmt.executeUpdate();

            System.out.println("Book returned successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}