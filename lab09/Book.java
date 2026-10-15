import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
   Lab 9 - Exercise 3: equals/hashCode based on ISBN only.
 */
public class Book {
    private final String isbn;
    private final String title;

    public Book(String isbn, String title) {
        this.isbn = Objects.requireNonNull(isbn, "isbn");
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book other)) {
            return false;
        }
        return isbn.equals(other.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public String toString() {
        return "Book{isbn='" + isbn + "', title='" + title + "'}";
    }

    public static void main(String[] args) {
        Set<Book> library = new HashSet<>();

        System.out.println("Add 1st copy: " + library.add(new Book("978-0134685991", "Effective Java")));
        System.out.println("Add duplicate ISBN: " + library.add(new Book("978-0134685991", "Effective Java (2nd printing)")));
        System.out.println("Add different book: " + library.add(new Book("978-0132350884", "Clean Code")));

        System.out.println("Set size: " + library.size() + " (the duplicate was rejected)");
        library.forEach(System.out::println);
    }
}
