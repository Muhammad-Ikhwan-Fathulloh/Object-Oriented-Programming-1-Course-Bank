import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Ganti dengan informasi koneksi Anda
    private static final String URL = "jdbc:mysql://localhost:3306/nama_database"; // Ganti dengan nama database Anda
    private static final String USER = "username"; // Ganti dengan username database Anda
    private static final String PASSWORD = "password"; // Ganti dengan password database Anda

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Membuat koneksi ke database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi berhasil!");
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        } finally {
            // Menutup koneksi
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Koneksi ditutup.");
                } catch (SQLException e) {
                    System.out.println("Gagal menutup koneksi: " + e.getMessage());
                }
            }
        }
    }
}