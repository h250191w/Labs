package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {

    public static final int MAX_LOANS = 3;

    private final String id;
    private final String name;
    private final List<Loan> activeLoans = new ArrayList<>();

    public Member(String id, String name) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Member id is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Member name is required");
        }
        this.id = id.trim();
        this.name = name.trim();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /** @return a read-only view of the member's current loans. */
    public List<Loan> getActiveLoans() {
        return Collections.unmodifiableList(activeLoans);
    }

    public boolean canBorrowMore() {
        return activeLoans.size() < MAX_LOANS;
    }

    void addLoan(Loan loan) {
        activeLoans.add(loan);
    }

    void removeLoan(Loan loan) {
        activeLoans.remove(loan);
    }

    @Override
    public String toString() {
        return String.format("%-6s %-25s loans: %d/%d", id, name, activeLoans.size(), MAX_LOANS);
    }
}
