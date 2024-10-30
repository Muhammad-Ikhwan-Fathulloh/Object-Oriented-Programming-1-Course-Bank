// Main.java
public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Tambah buku
        Book book1 = new Book("B001", "Java Programming", "Tekno");
        Book book2 = new Book("B002", "Data Structures", "Tekno");
        library.addBook(book1);
        library.addBook(book2);

        // Tambah anggota
        Member member1 = new Member("M001", "Alice");
        library.addMember(member1);

        // Peminjaman buku
        library.borrowBook("B001", "M001");

        // Pengembalian buku
        library.returnBook("B001");
    }
}
