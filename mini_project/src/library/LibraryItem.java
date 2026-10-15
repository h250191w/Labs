package library;

import java.util.List;

/**
  Base class for everything the library lends out.
  Subclasses decide their own loan period (polymorphism) and extra CSV details.
 */
public abstract class LibraryItem implements Borrowable {

    private final String id;
    private final String title;
    private boolean available = true;

    protected LibraryItem(String id, String title) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Item id is required");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Item title is required");
        }
        this.id = id.trim();
        this.title = title.trim();
    }

    /** Number of days this kind of item may be kept. */
    public abstract int loanPeriodDays();

    /** Short label such as "BOOK" or "DVD"; also used as the first CSV column. */
    public abstract String typeName();

    /** Subclass-specific values stored after id and title in the CSV file. */
    public abstract List<String> extraFields();

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    /** Package-private: only the library package may change availability directly. */
    void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public void borrow(Member member) throws ItemNotAvailableException, MemberLimitExceededException {
        if (!available) {
            throw new ItemNotAvailableException(this);
        }
        if (!member.canBorrowMore()) {
            throw new MemberLimitExceededException(member);
        }
        available = false;          // only changed after every check has passed
    }

    @Override
    public void giveBack() {
        if (available) {
            throw new IllegalStateException("\"" + title + "\" is not on loan");
        }
        available = true;
    }

    @Override
    public String toString() {
        return String.format("%-4s %-6s %-40s %s (%d-day loan)",
                typeName(), id, title, available ? "available" : "ON LOAN", loanPeriodDays());
    }
}
