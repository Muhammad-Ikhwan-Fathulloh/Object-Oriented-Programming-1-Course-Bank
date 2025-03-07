# Judul Proyek: Sistem Manajemen Perpustakaan

Proyek ini melibatkan 4 pilar OOP: **Encapsulation**, **Abstraction**, **Inheritance**, dan **Polymorphism** melalui sistem sederhana yang mengelola buku dan anggota perpustakaan.

---

## 1. Encapsulation (Enkapsulasi)
Menerapkan **Encapsulation** dengan menyembunyikan data dalam kelas dan hanya memperbolehkan akses melalui getter dan setter. Data seperti judul buku, penulis, dan status ketersediaan buku diisolasi dan hanya dapat diakses melalui metode yang ditentukan, bukan langsung dari variabel.

<a href="https://github.com/Muhammad-Ikhwan-Fathulloh/Object-Oriented-Programming-1-Course-Bank/blob/main/MiniProject/LibrarySystem/Book.java">Lihat Kode</a>

---

## 2. Abstraction (Abstraksi)
Menggunakan **Abstraction** dengan mendefinisikan `LibraryMember` sebagai kelas abstrak. Kelas ini menyimpan informasi dasar tentang anggota perpustakaan seperti nama dan ID anggota, serta memiliki metode umum untuk meminjam buku, tetapi tidak memedulikan detail implementasinya. Kelas abstrak ini tidak bisa diinstansiasi, tetapi menjadi dasar untuk kelas turunan.

<a href="https://github.com/Muhammad-Ikhwan-Fathulloh/Object-Oriented-Programming-1-Course-Bank/blob/main/MiniProject/LibrarySystem/LibraryMember.java">Lihat Kode</a>

---

## 3. Inheritance (Pewarisan)
Menerapkan **Inheritance** dengan membuat kelas `Student` dan `Teacher` yang mewarisi sifat dari kelas `LibraryMember`. Kedua kelas turunan ini menggunakan atribut dan metode yang diwariskan dari `LibraryMember`, tetapi menambahkan perilaku spesifik untuk masing-masing jenis anggota, seperti aturan yang berbeda dalam peminjaman buku.

<a href="https://github.com/Muhammad-Ikhwan-Fathulloh/Object-Oriented-Programming-1-Course-Bank/blob/main/MiniProject/LibrarySystem/Student.java">Lihat Kode Student</a>
<a href="https://github.com/Muhammad-Ikhwan-Fathulloh/Object-Oriented-Programming-1-Course-Bank/blob/main/MiniProject/LibrarySystem/Teacher.java">Lihat Kode Teacher</a>

---

## 4. Polymorphism (Polimorfisme)
**Polymorphism** ditunjukkan ketika objek `LibraryMember` digunakan secara polimorfis, di mana objek dari kelas `Student` dan `Teacher` dapat diperlakukan sebagai objek dari tipe dasar `LibraryMember`. Ini memungkinkan kita menggunakan metode umum seperti `borrowBook` pada berbagai tipe objek, tanpa mengetahui detail tipe sebenarnya dari objek tersebut.

<a href="https://github.com/Muhammad-Ikhwan-Fathulloh/Object-Oriented-Programming-1-Course-Bank/blob/main/MiniProject/LibrarySystem/LibrarySystem.java">Lihat Kode</a>

---

## Penjelasan Penerapan 4 Pilar OOP:

- **Encapsulation**: Data pribadi seperti `title`, `author`, dan `isAvailable` disembunyikan dalam kelas `Book`, dan hanya dapat diakses melalui metode getter dan setter untuk menjaga integritas data.
- **Abstraction**: Kelas `LibraryMember` adalah kelas abstrak yang mendefinisikan atribut dan metode umum terkait anggota perpustakaan, tanpa memaparkan detail peminjaman buku. Implementasi spesifiknya ditentukan dalam kelas turunan.
- **Inheritance**: Kelas `Student` dan `Teacher` mewarisi atribut dan metode dari kelas `LibraryMember`, sehingga menghindari duplikasi kode dan mempermudah penambahan perilaku spesifik pada tiap kelas.
- **Polymorphism**: Objek dari kelas `Student` dan `Teacher` dapat digunakan sebagai tipe `LibraryMember` dalam metode `borrowBook`, memungkinkan penggunaan fleksibel dari berbagai jenis objek dengan cara yang seragam.

---

## Flowchart

[![](https://mermaid.ink/img/pako:eNp1kcFuwjAMhl_FymmTQLv3sIlSYJPGLuWytRzc1qURbYxSZwhR3n2hLdOEtJxs_1_i3_FZ5VyQClRZ8zGv0ApsotSAP7PkXWcW7WlNTUZ2C9Ppc6dNRVZL20GYxOIKMrId8Tt9nmwIc5-NenjVIXrI2Fo-hsz7x0GY_ydEvbBIrqXxkUXfZF5Rvgf8Rl1jpmstpw6WZ93OhkpNL5cBX_b4J3k7qyQmAWzBGbxh27_UB3fw2vcCwwL30Ko383Yb-mkcDgbXLUhFPvZG1UQ1ZBvUhf_V8_VyqrzYUKoCHxZUoqslVam5eBSdcHwyuQrEOpooy25XqaDEuvWZOxQoFGncWWxuyAHNF3PzC1Ghhe16WGO_zcsPxgWaRw?type=png)](https://mermaid.live/edit#pako:eNp1kcFuwjAMhl_FymmTQLv3sIlSYJPGLuWytRzc1qURbYxSZwhR3n2hLdOEtJxs_1_i3_FZ5VyQClRZ8zGv0ApsotSAP7PkXWcW7WlNTUZ2C9Ppc6dNRVZL20GYxOIKMrId8Tt9nmwIc5-NenjVIXrI2Fo-hsz7x0GY_ydEvbBIrqXxkUXfZF5Rvgf8Rl1jpmstpw6WZ93OhkpNL5cBX_b4J3k7qyQmAWzBGbxh27_UB3fw2vcCwwL30Ko383Yb-mkcDgbXLUhFPvZG1UQ1ZBvUhf_V8_VyqrzYUKoCHxZUoqslVam5eBSdcHwyuQrEOpooy25XqaDEuvWZOxQoFGncWWxuyAHNF3PzC1Ghhe16WGO_zcsPxgWaRw)

---

### Penjelasan Flowchart

1. **LibraryMember** adalah kelas induk yang diwarisi oleh kelas **Student** dan **Teacher**.
2. Kedua kelas turunan ini menggunakan metode `borrowBook()`.
3. Metode `borrowBook()` memanggil kelas **Book**, di mana ketersediaan buku diperiksa.
4. Jika buku **tersedia**, status buku diubah menjadi **tidak tersedia**, dan anggota perpustakaan (student atau teacher) berhasil meminjam buku.
5. Jika buku **tidak tersedia**, maka muncul pemberitahuan bahwa buku tidak tersedia.