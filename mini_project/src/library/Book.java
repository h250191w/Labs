package library;

import java.util.List;

public class Book extends LibraryItem {

    private static final int LOAN_PERIOD_DAYS = 21;

    private final String author;

    public Book(String id, String title, String author) {
        super(id, title);
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Book author is required");
        }
        this.author = author.trim();
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public int loanPeriodDays() {
        return LOAN_PERIOD_DAYS;
    }

    @Override
    public String typeName() {
        return "BOOK";
    }

    @Override
    public List<String> extraFields() {
        return List.of(author);
    }

    @Override
    public String toString() {
        return super.toString() + " by " + author;
    }
}
