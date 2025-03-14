# Koneksi Database Menggunakan Java

Berikut adalah langkah-langkah untuk membuat koneksi ke database menggunakan Java. Saya akan memberikan contoh untuk koneksi ke database MySQL, tetapi prinsipnya serupa untuk database lain (dengan beberapa penyesuaian pada URL koneksi dan driver).

---

## 1. Install Laragon/XAMPP/Lainnya dan Tambahkan Library JDBC

## Langkah 1: Unduh dan Install Laragon atau XAMPP
- **Laragon**: Unduh dari [Laragon Official Website](https://laragon.org/download/).  
- **XAMPP**: Unduh dari [XAMPP Official Website](https://www.apachefriends.org/index.html).

Keduanya menyediakan lingkungan pengembangan yang lengkap dengan server web dan database. Pilih salah satu sesuai kebutuhan Anda.

---

## Langkah 2: Konfigurasi Database dengan phpMyAdmin
- **phpMyAdmin**: Gunakan [phpMyAdmin](https://www.phpmyadmin.net/) untuk mempermudah pengelolaan database MySQL/MariaDB.  
- Pastikan Anda dapat mengakses phpMyAdmin melalui Laragon atau XAMPP setelah instalasi. Biasanya diakses melalui `http://localhost/phpmyadmin`.

---

## Langkah 3: Tambahkan Library JDBC untuk MySQL
Pastikan Anda memiliki driver JDBC untuk database yang Anda gunakan. Untuk MySQL, Anda bisa mengunduh driver dari [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) atau menggunakan Maven dengan menambahkan dependensi berikut di `pom.xml`:

```xml
<dependencies>
    <dependency>
      <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.32</version>
    </dependency>
</dependencies>
```

---

# Panduan Koneksi JDBC untuk Java di Visual Studio Code

## Persiapan

### a. Install Visual Studio Code
- Unduh dan install Visual Studio Code dari [VS Code Official Website](https://code.visualstudio.com/).

### b. Install JDK
- Pastikan Anda memiliki JDK terinstal. Unduh dari [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) atau [OpenJDK](https://openjdk.org/).
- Verifikasi instalasi JDK dengan menjalankan perintah berikut di terminal:

  ```bash
  java -version
  ```

### c. Install Extensions di VS Code
- Extension Pack for Java: Instal melalui Extension Marketplace di VS Code untuk mendukung pengembangan Java.

### d. Unduh MySQL Connector/J
- Unduh driver JDBC dari MySQL Connector/J.
- Atau gunakan Maven untuk menambahkan dependensi.

---

## Konfigurasi Proyek Java

### a. Membuat Proyek Baru
- Buka VS Code.
- Buat folder baru untuk proyek Anda.
- Jalankan perintah berikut di terminal:
  
```bash
mkdir jdbc-example
cd jdbc-example
mkdir src
```

- Buat file baru bernama Main.java di dalam folder src.

### b. Tambahkan Driver MySQL ke Classpath
#### Jika menggunakan driver .jar manual:
- Buat folder lib di dalam proyek:
  
```bash
mkdir lib
```

- Salin file .jar dari MySQL Connector/J ke folder lib.
- Tambahkan file .jar ke classpath di VS Code:
  Klik kanan pada proyek > Add Folder to Java Project > pilih folder lib.

#### Jika menggunakan Maven:
- Buat file pom.xml di root proyek dan tambahkan dependensi berikut:

```xml
<dependencies>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.32</version> <!-- Ganti dengan versi terbaru -->
    </dependency>
</dependencies>
```

- Jalankan perintah berikut untuk mengunduh dependensi Maven:

```bash
mvn install
```

---

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

---

## 3. Mengatasi Masalah
- Pastikan database MySQL berjalan dan dapat diakses.
- Pastikan informasi URL, username, dan password yang digunakan sudah benar.
- Jika menggunakan IDE seperti IntelliJ atau Eclipse, pastikan library JDBC telah ditambahkan ke classpath proyek.

---

# Latihan Todo...

---

## Buat Database dan Tabel di MySQL

Buat Database dan Tabel di MySQL
```sql
CREATE DATABASE todo_system;

USE todo_system;

CREATE TABLE todos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    is_completed BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

[Download Kode](Database/database.sql)  

---

## Buat Database Connection

DatabaseConnection.java
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/your_database_name"; // Ganti 'your_database_name' dengan nama database Anda
    private static final String USER = "your_username"; // Ganti 'your_username' dengan username database Anda
    private static final String PASSWORD = "your_password"; // Ganti 'your_password' dengan password database Anda

    private static Connection connection;

    static {
        try {
            // Memuat driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Membuat koneksi ke database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Database connection established successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to establish database connection.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close database connection.");
                e.printStackTrace();
            }
        }
    }
}
```

[Lihat Kode](Database/DatabaseConnection.java)  

---

## Buat Todo

Todo.java
```java
public class Todo {
    private int id;
    private String title;
    private String description;
    private boolean isCompleted;
    private String createdAt;

    public Todo(int id, String title, String description, boolean isCompleted, String createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.createdAt = createdAt;
    }

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
        this.isCompleted = false;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
```

[Lihat Kode](Database/Todo.java)  

---

## Buat Todo Operations

TodoOperations.java
```java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoOperations {
    private Connection connection;

    public TodoOperations() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    // Create
    public void addTodo(Todo todo) {
        String query = "INSERT INTO todos (title, description) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getDescription());
            stmt.executeUpdate();
            System.out.println("To-Do added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<>();
        String query = "SELECT * FROM todos";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                todos.add(new Todo(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getBoolean("is_completed"),
                        rs.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    // Update
    public void updateTodo(int id, String newTitle, String newDescription) {
        String query = "UPDATE todos SET title = ?, description = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newTitle);
            stmt.setString(2, newDescription);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("To-Do updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteTodo(int id) {
        String query = "DELETE FROM todos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("To-Do deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mark as Completed
    public void markAsCompleted(int id) {
        String query = "UPDATE todos SET is_completed = TRUE WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("To-Do marked as completed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

[Lihat Kode](Database/TodoOperations.java)  

---

## Buat Main

Main.java
```java
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            TodoOperations operations = new TodoOperations();
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n--- To-Do List Menu ---");
                System.out.println("1. Add To-Do");
                System.out.println("2. View All To-Dos");
                System.out.println("3. Update To-Do");
                System.out.println("4. Delete To-Do");
                System.out.println("5. Mark To-Do as Completed");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter description: ");
                        String description = scanner.nextLine();
                        operations.addTodo(new Todo(title, description));
                        break;

                    case 2:
                        System.out.println("All To-Dos:");
                        operations.getTodos().forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("Enter To-Do ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter new title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter new description: ");
                        String newDescription = scanner.nextLine();
                        operations.updateTodo(updateId, newTitle, newDescription);
                        break;

                    case 4:
                        System.out.print("Enter To-Do ID to delete: ");
                        int deleteId = scanner.nextInt();
                        operations.deleteTodo(deleteId);
                        break;

                    case 5:
                        System.out.print("Enter To-Do ID to mark as completed: ");
                        int completeId = scanner.nextInt();
                        operations.markAsCompleted(completeId);
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } while (choice != 0);

            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

[Lihat Kode](Database/Main.java)  

---

## Latihan Proyek Perpustakaan
[Lihat Materi](material.html?pertemuan=Database&materi=Database/LibrarySystem/README.md)  
