# Koneksi Database Menggunakan Java

Berikut adalah langkah-langkah untuk membuat koneksi ke database menggunakan Java. Saya akan memberikan contoh untuk koneksi ke database MySQL, tetapi prinsipnya serupa untuk database lain (dengan beberapa penyesuaian pada URL koneksi dan driver).

---

## 1. Tambahkan Library JDBC
Pastikan Anda memiliki driver JDBC untuk database yang Anda gunakan. Untuk MySQL, Anda bisa mengunduh driver dari [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) atau menggunakan Maven dengan menambahkan dependensi berikut di `pom.xml`:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.32</version> <!-- Versi terbaru -->
</dependency>
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
