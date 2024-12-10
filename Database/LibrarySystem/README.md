# Library System dengan Konsep OOP di Java

Sistem perpustakaan ini dirancang menggunakan konsep **Object-Oriented Programming (OOP)** dengan implementasi berbagai elemen seperti **Inheritance**, **Polymorphism**, **Abstract**, dan **Encapsulation**. Berikut adalah penjelasan mengenai desain sistem dan konsep yang digunakan:

---

## 1. Struktur Database
Sistem ini menggunakan MySQL untuk menyimpan data. Struktur database terdiri dari tabel berikut:
- **books**: Menyimpan data buku, seperti `id`, `title`, `author`, dan status `is_available`.
- **users**: Menyimpan data pengguna, seperti `id`, `name`, dan `email`.
- **transactions**: Menyimpan data peminjaman buku oleh pengguna, termasuk `borrow_date` dan `return_date`.

---

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
[Lihat Kode](Database/LibrarySystem/Book.java)  
[Lihat Kode](Database/LibrarySystem/User.java)  

---

## 2. Penggunaan Konsep OOP
### a. Abstract Class
- **Abstract Class (`LibraryEntity`)**: Digunakan untuk mendefinisikan entitas dasar dengan properti umum seperti `id`. 
- Abstract class ini juga memiliki metode abstrak `getDetails()` yang harus diimplementasikan oleh semua class turunannya, seperti `Book` dan `User`.

### b. Inheritance
- Class `Book` dan `User` merupakan turunan dari class `LibraryEntity`.
- Class ini mewarisi properti `id` dari `LibraryEntity` dan menambahkan properti spesifik masing-masing, seperti `title`, `author` untuk buku, serta `name`, `email` untuk pengguna.

### c. Polymorphism
- Polimorfisme diwujudkan melalui metode `getDetails()` yang didefinisikan di class `LibraryEntity`. 
- Setiap class turunan (`Book` dan `User`) mengimplementasikan metode ini dengan caranya sendiri, sesuai dengan atribut masing-masing.

### d. Encapsulation
- Semua atribut (seperti `title`, `author`, `name`, dan `email`) bersifat **private** untuk melindungi data.
- Akses terhadap atribut-atribut ini hanya diperbolehkan melalui getter dan setter yang bersifat **public**, sehingga menjaga kontrol penuh terhadap data.

---

## 3. Interface untuk Operasi Perpustakaan
- **Interface (`LibraryOperations`)**: Mendefinisikan operasi-operasi yang dilakukan dalam sistem perpustakaan, seperti:
  - Menambahkan buku (`addBook`).
  - Melihat daftar buku (`viewBooks`).
  - Meminjam buku (`borrowBook`).
  - Mengembalikan buku (`returnBook`).

[Lihat Kode](Database/LibrarySystem/LibraryOperations.java)  

---

## 4. Class Utama (LibrarySystem)
- **Implements Interface**: Class ini mengimplementasikan interface `LibraryOperations` dan menyediakan implementasi untuk semua metode yang didefinisikan di dalamnya.
- **Database Connection**: Menghubungkan aplikasi dengan MySQL menggunakan JDBC. Operasi database seperti `SELECT`, `INSERT`, `UPDATE` dilakukan di class ini.
- **Logika Peminjaman Buku**:
  - Mengecek ketersediaan buku sebelum meminjam.
  - Mengupdate status buku jika dipinjam atau dikembalikan.
  - Menambahkan catatan transaksi peminjaman ke tabel `transactions`.

[Lihat Kode](Database/LibrarySystem/LibrarySystem.java)  

---

## 5. Class Main
- **Menu Interaktif**: Memberikan antarmuka berbasis teks untuk berinteraksi dengan sistem perpustakaan.
- **Operasi yang Didukung**:
  1. Menambahkan buku baru.
  2. Melihat daftar buku.
  3. Meminjam buku berdasarkan ID buku dan ID pengguna.
  4. Mengembalikan buku yang dipinjam.

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

## 7. Kelebihan Desain
1. **Reusability**: Class seperti `LibraryEntity` dapat digunakan kembali untuk mendefinisikan entitas lain di masa depan.
2. **Flexibility**: Menggunakan interface memungkinkan pengembangan logika tambahan tanpa mengubah kode yang sudah ada.
3. **Scalability**: Sistem mudah diintegrasikan dengan fitur baru, seperti pengelolaan anggota perpustakaan atau laporan statistik.