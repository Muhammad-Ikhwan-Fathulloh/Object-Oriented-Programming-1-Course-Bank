# Library System dengan Konsep OOP di Java

Sistem perpustakaan ini dirancang menggunakan konsep **Object-Oriented Programming (OOP)** dengan implementasi berbagai elemen seperti **Inheritance**, **Polymorphism**, **Abstract**, dan **Encapsulation**. Berikut adalah penjelasan mengenai desain sistem dan konsep yang digunakan:

---

## 1. Struktur Database
Sistem ini menggunakan MySQL untuk menyimpan data. Struktur database terdiri dari tabel berikut:
- **books**: Menyimpan data buku, seperti `id`, `title`, `author`, dan status `is_available`.
- **users**: Menyimpan data pengguna, seperti `id`, `name`, dan `email`.
- **transactions**: Menyimpan data peminjaman buku oleh pengguna, termasuk `borrow_date` dan `return_date`.

---

Buat Database dan Tabel di MySQL
```sql
CREATE DATABASE library_system;

USE library_system;

CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    is_available BOOLEAN DEFAULT TRUE
);

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    user_id INT NOT NULL,
    borrow_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    return_date TIMESTAMP NULL,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

[Download Kode](Database/LibrarySystem/database.sql)  

---

DatabaseConnection.java
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

[Lihat Kode](Database/LibrarySystem/DatabaseConnection.java)  

---

## 2. Penggunaan Konsep OOP
### a. Abstract Class
- **Abstract Class (`LibraryEntity`)**: Digunakan untuk mendefinisikan entitas dasar dengan properti umum seperti `id`. 
- Abstract class ini juga memiliki metode abstrak `getDetails()` yang harus diimplementasikan oleh semua class turunannya, seperti `Book` dan `User`.

LibraryEntity.java
```java
public abstract class LibraryEntity {
    private int id;

    public LibraryEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract String getDetails(); // Polymorphism
}
```

[Lihat Kode](Database/LibrarySystem/LibraryEntity.java)  

---

### b. Inheritance
- Class `Book` dan `User` merupakan turunan dari class `LibraryEntity`.
- Class ini mewarisi properti `id` dari `LibraryEntity` dan menambahkan properti spesifik masing-masing, seperti `title`, `author` untuk buku, serta `name`, `email` untuk pengguna.

Book.java
```java
public class Book extends LibraryEntity {
    String title;
    String author;
    boolean isAvailable;

    public Book(int id, String title, String author, boolean isAvailable) {
        super(id);
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }
}
```

[Lihat Kode](Database/LibrarySystem/Book.java)  

User.java
```java
public class User extends LibraryEntity {
    String name;
    String email;

    public User(int id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }
}
```

[Lihat Kode](Database/LibrarySystem/User.java)  

---

### c. Polymorphism
- Polimorfisme diwujudkan melalui metode `getDetails()` yang didefinisikan di class `LibraryEntity`. 
- Setiap class turunan (`Book` dan `User`) mengimplementasikan metode ini dengan caranya sendiri, sesuai dengan atribut masing-masing.

LibraryEntity.java
```java
public abstract String getDetails(); // Polymorphism
```

[Lihat Kode](Database/LibrarySystem/LibraryEntity.java)  

Book.java
```java
    @Override
    public String getDetails() {
        return "Book [ID=" + getId() + ", Title=" + title + ", Author=" + author + ", Available=" + isAvailable + "]";
    }
```

[Lihat Kode](Database/LibrarySystem/Book.java)  

User.java
```java
    @Override
    public String getDetails() {
        return "User [ID=" + getId() + ", Name=" + name + ", Email=" + email + "]";
    }
```

[Lihat Kode](Database/LibrarySystem/User.java)  

---

### d. Encapsulation
- Semua atribut (seperti `title`, `author`, `name`, dan `email`) bersifat **private** untuk melindungi data.
- Akses terhadap atribut-atribut ini hanya diperbolehkan melalui getter dan setter yang bersifat **public**, sehingga menjaga kontrol penuh terhadap data.

Book.java
```java
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
```

[Lihat Kode](Database/LibrarySystem/Book.java)  

User.java
```java
public class User extends LibraryEntity {
    private String name;
    private String email;

    public User(int id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String getDetails() {
        return "User [ID=" + getId() + ", Name=" + name + ", Email=" + email + "]";
    }
}
```

[Lihat Kode](Database/LibrarySystem/User.java)  

---

## 3. Interface untuk Operasi Perpustakaan
- **Interface (`LibraryOperations`)**: Mendefinisikan operasi-operasi yang dilakukan dalam sistem perpustakaan, seperti:
  - Menambahkan buku (`addBook`).
  - Melihat daftar buku (`viewBooks`).
  - Meminjam buku (`borrowBook`).
  - Mengembalikan buku (`returnBook`).

LibraryOperations.java
```java
public interface LibraryOperations {
    void addBook(Book book);
    void viewBooks();
    void borrowBook(int bookId, int userId);
    void returnBook(int bookId);
}
```

[Lihat Kode](Database/LibrarySystem/LibraryOperations.java)  

---

## 4. Class Utama (LibrarySystem)
- **Implements Interface**: Class ini mengimplementasikan interface `LibraryOperations` dan menyediakan implementasi untuk semua metode yang didefinisikan di dalamnya.
- **Database Connection**: Menghubungkan aplikasi dengan MySQL menggunakan JDBC. Operasi database seperti `SELECT`, `INSERT`, `UPDATE` dilakukan di class ini.
- **Logika Peminjaman Buku**:
  - Mengecek ketersediaan buku sebelum meminjam.
  - Mengupdate status buku jika dipinjam atau dikembalikan.
  - Menambahkan catatan transaksi peminjaman ke tabel `transactions`.

LibrarySystem.java
```java
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
```

[Lihat Kode](Database/LibrarySystem/LibrarySystem.java)  

---

## 5. Class Main
- **Menu Interaktif**: Memberikan antarmuka berbasis teks untuk berinteraksi dengan sistem perpustakaan.
- **Operasi yang Didukung**:
  1. Menambahkan buku baru.
  2. Melihat daftar buku.
  3. Meminjam buku berdasarkan ID buku dan ID pengguna.
  4. Mengembalikan buku yang dipinjam.

Main.java
```java
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            LibrarySystem librarySystem = new LibrarySystem();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nLibrary System Menu:");
                System.out.println("1. Add Book");
                System.out.println("2. View Books");
                System.out.println("3. Borrow Book");
                System.out.println("4. Return Book");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter book title: ");
                        String title = scanner.next();
                        System.out.print("Enter book author: ");
                        String author = scanner.next();
                        librarySystem.addBook(new Book(0, title, author, true));
                        break;
                    case 2:
                        librarySystem.viewBooks();
                        break;
                    case 3:
                        System.out.print("Enter book ID to borrow: ");
                        int bookId = scanner.nextInt();
                        System.out.print("Enter user ID: ");
                        int userId = scanner.nextInt();
                        librarySystem.borrowBook(bookId, userId);
                        break;
                    case 4:
                        System.out.print("Enter book ID to return: ");
                        int returnBookId = scanner.nextInt();
                        librarySystem.returnBook(returnBookId);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

[Lihat Kode](Database/LibrarySystem/Main.java)  

---

## 6. Konsep OOP yang Diterapkan
### **a. Inheritance**
- `Book` dan `User` mewarisi class abstrak `LibraryEntity`, sehingga dapat menggunakan properti umum seperti `id`.

### **b. Polymorphism**
- Metode `getDetails()` diimplementasikan berbeda oleh class `Book` dan `User` untuk memberikan informasi sesuai konteks masing-masing.

### **c. Encapsulation**
- Data sensitif seperti `title`, `author`, dan `isAvailable` dilindungi menggunakan akses **private** dan diakses melalui **getter** dan **setter**.

### **d. Abstract**
- Class `LibraryEntity` tidak dapat diinstansiasi secara langsung, tetapi menjadi cetakan umum untuk class `Book` dan `User`.

---

# Selanjutnya, Terus Berproses...