# Proyek: Sistem Manajemen Perpustakaan

Dalam proyek ini, kita akan membuat sistem manajemen perpustakaan yang terdiri dari **buku**, **anggota**, dan **peminjaman buku**. Fitur utama yang akan kita bangun adalah kemampuan untuk menambah anggota, menambah buku, serta meminjam dan mengembalikan buku.

## Langkah 1: Buat Struktur Proyek
1. Buat folder proyek, misalnya `LibraryManagementSystem`.
2. Di dalamnya, buat subfolder `src` untuk menyimpan file Java.

## Langkah 2: Buat Kelas dan Struktur Dasar
1. Buat **Kelas Buku** (`Book.java`)
2. Buat **Kelas Anggota** (`Member.java`)
3. Buat **Kelas Peminjaman** (`Library.java`)
4. Buat **Kelas Utama** (`Main.java`) untuk menjalankan program

## Langkah 3: Implementasi Kode Java
Berikut adalah penjelasan untuk masing-masing kelas.

### 1. Kelas Buku (`Book.java`)
Kelas ini menyimpan informasi buku, seperti **ID buku**, **judul**, **pengarang**, dan **status ketersediaan**.

**Penjelasan:**
- Kelas `Book` memiliki **getter** dan **setter** untuk mengakses dan mengubah status ketersediaan buku.
- Metode `toString` memberikan deskripsi buku saat dipanggil.

### 2. Kelas Anggota (`Member.java`)
Kelas ini menyimpan informasi anggota perpustakaan, seperti **ID anggota** dan **nama anggota**.

**Penjelasan:**
- Kelas `Member` menyimpan informasi tentang anggota perpustakaan.
- Metode `toString` digunakan untuk menampilkan data anggota.

### 3. Kelas Peminjaman (`Library.java`)
Kelas ini mengelola buku dan anggota, serta menyediakan fungsionalitas untuk **penambahan buku**, **penambahan anggota**, **peminjaman buku**, dan **pengembalian buku**.

**Penjelasan:**
- `addBook` dan `addMember` digunakan untuk menambah buku dan anggota.
- `borrowBook` dan `returnBook` digunakan untuk meminjam dan mengembalikan buku.
- `findBook` berfungsi untuk mencari buku berdasarkan ID buku yang diberikan.

### 4. Kelas Utama (`Main.java`)
Kelas ini menjalankan program dan mendemonstrasikan fungsionalitas sistem.

**Penjelasan:**
- Kelas `Main` membuat instance dari kelas `Library`, menambahkan beberapa buku dan anggota, serta melakukan peminjaman dan pengembalian buku.

---

## Penjelasan Konsep OOP dalam Proyek Ini

- **Enkapsulasi**: Variabel `id`, `title`, dan `author` dalam kelas `Book` serta `id` dan `name` dalam `Member` bersifat `private` dan hanya dapat diakses melalui metode getter dan setter.
  
- **Abstraksi**: Kelas `Library` menyembunyikan kompleksitas fungsi-fungsi `borrowBook`, `returnBook`, dan `findBook` dari pengguna.
  
- **Pewarisan**: Meskipun tidak ada pewarisan eksplisit dalam contoh ini, konsep ini bisa diperluas dengan menambahkan kelas turunan seperti `Ebook` yang mewarisi `Book`.
  
- **Polimorfisme**: Diimplementasikan melalui metode `toString` pada `Book` dan `Member` untuk menyediakan tampilan string yang berbeda sesuai dengan masing-masing kelas.
