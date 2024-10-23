public class LibrarySystem {
    public static void main(String[] args) {
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald");
        Book book2 = new Book("1984", "George Orwell");

        LibraryMember student = new Student("Alice", 1001);
        LibraryMember teacher = new Teacher("Mr. Smith", 2001);

        // Polymorphism: Both student and teacher are treated as LibraryMember
        student.borrowBook(book1); // Alice (Student) borrows "The Great Gatsby"
        teacher.borrowBook(book1); // "The Great Gatsby" is already borrowed, so Mr. Smith can't borrow it.
        teacher.borrowBook(book2); // Mr. Smith (Teacher) borrows "1984"
    }
}