package library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/** A record of one item being borrowed by one member. */
public class Loan {

    private final LibraryItem item;
    private final Member member;
    private final LocalDate borrowedOn;
    private final LocalDate dueDate;

    public Loan(LibraryItem item, Member member, LocalDate borrowedOn) {
        this.item = Objects.requireNonNull(item, "item");
        this.member = Objects.requireNonNull(member, "member");
        this.borrowedOn = Objects.requireNonNull(borrowedOn, "borrowedOn");
        this.dueDate = borrowedOn.plusDays(item.loanPeriodDays());   // polymorphic loan period
    }

    public LibraryItem getItem() {
        return item;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getBorrowedOn() {
        return borrowedOn;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isOverdue(LocalDate asOf) {
        return asOf.isAfter(dueDate);
    }

    public long daysOverdue(LocalDate asOf) {
        return Math.max(0, ChronoUnit.DAYS.between(dueDate, asOf));
    }

    @Override
    public String toString() {
        return String.format("\"%s\" (%s) borrowed by %s on %s, due %s",
                item.getTitle(), item.getId(), member.getName(), borrowedOn, dueDate);
    }
}
