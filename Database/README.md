# Koneksi Database Menggunakan Java

Berikut adalah langkah-langkah untuk membuat koneksi ke database menggunakan Java. Saya akan memberikan contoh untuk koneksi ke database MySQL, tetapi prinsipnya serupa untuk database lain (dengan beberapa penyesuaian pada URL koneksi dan driver).

## 1. Tambahkan Library JDBC
Pastikan Anda memiliki driver JDBC untuk database yang Anda gunakan. Untuk MySQL, Anda bisa mengunduh driver dari [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) atau menggunakan Maven dengan menambahkan dependensi berikut di `pom.xml`:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.32</version> <!-- Versi terbaru -->
</dependency>
```

## 2. Kode Koneksi Database
Berikut adalah contoh kode untuk melakukan koneksi ke database MySQL:

```java
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
```

## Penjelasan Kode
- **DriverManager**: Kelas ini digunakan untuk membuat koneksi ke database.
- **getConnection(URL, USER, PASSWORD)**: Metode ini digunakan untuk membuat koneksi. URL mengandung informasi lokasi database dan nama database.
- **Connection**: Objek yang mewakili koneksi ke database. Pastikan untuk menutup koneksi saat selesai.

## 3. Mengatasi Masalah
- Pastikan database MySQL berjalan dan dapat diakses.
- Pastikan informasi URL, username, dan password yang digunakan sudah benar.
- Jika menggunakan IDE seperti IntelliJ atau Eclipse, pastikan library JDBC telah ditambahkan ke classpath proyek.
