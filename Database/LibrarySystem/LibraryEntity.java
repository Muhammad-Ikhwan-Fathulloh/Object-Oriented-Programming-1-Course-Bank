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