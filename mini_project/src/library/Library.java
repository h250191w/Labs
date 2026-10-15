package library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
  Core service: owns the items, members and active loans and enforces the lending rules.
 */
public class Library {

    private final Map<String, LibraryItem> items = new LinkedHashMap<>();
    private final Map<String, Member> members = new LinkedHashMap<>();
    private final List<Loan> loans = new ArrayList<>();

    // ---------- registration ----------

    public void addItem(LibraryItem item) {
        if (items.containsKey(item.getId())) {
            throw new IllegalArgumentException("An item with id " + item.getId() + " already exists");
        }
        items.put(item.getId(), item);
    }

    public void registerMember(Member member) {
        if (members.containsKey(member.getId())) {
            throw new IllegalArgumentException("A member with id " + member.getId() + " already exists");
        }
        members.put(member.getId(), member);
    }

    // ---------- lending ----------

    public Loan borrow(String memberId, String itemId, LocalDate today)
            throws ItemNotAvailableException, MemberLimitExceededException {
        Member member = findMember(memberId);
        LibraryItem item = findItem(itemId);

        item.borrow(member);                         // validates, then marks the item unavailable
        Loan loan = new Loan(item, member, today);
        member.addLoan(loan);
        loans.add(loan);
        return loan;
    }

    public Loan returnItem(String memberId, String itemId) {
        Member member = findMember(memberId);
        LibraryItem item = findItem(itemId);

        Loan loan = loans.stream()
                .filter(l -> l.getMember() == member && l.getItem() == item)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        member.getName() + " has not borrowed \"" + item.getTitle() + "\""));

        item.giveBack();
        member.removeLoan(loan);
        loans.remove(loan);
        return loan;
    }

    /** Re-creates a saved loan when loading from disk, bypassing the lending checks. */
    void restoreLoan(String memberId, String itemId, LocalDate borrowedOn) {
        Member member = findMember(memberId);
        LibraryItem item = findItem(itemId);
        Loan loan = new Loan(item, member, borrowedOn);
        item.setAvailable(false);
        member.addLoan(loan);
        loans.add(loan);
    }

    // ---------- queries ----------

    public List<Loan> findOverdue(LocalDate asOf) {
        return loans.stream()
                .filter(loan -> loan.isOverdue(asOf))
                .sorted(Comparator.comparing(Loan::getDueDate))
                .toList();
    }

    public List<LibraryItem> searchByTitle(String text) {
        String needle = text.toLowerCase().trim();
        return items.values().stream()
                .filter(item -> item.getTitle().toLowerCase().contains(needle))
                .sorted(Comparator.comparing(LibraryItem::getTitle))
                .toList();
    }

    public LibraryItem findItem(String id) {
        LibraryItem item = items.get(id.trim());
        if (item == null) {
            throw new IllegalArgumentException("No item with id " + id);
        }
        return item;
    }

    public Member findMember(String id) {
        Member member = members.get(id.trim());
        if (member == null) {
            throw new IllegalArgumentException("No member with id " + id);
        }
        return member;
    }

    public Collection<LibraryItem> getItems() {
        return Collections.unmodifiableCollection(items.values());
    }

    public Collection<Member> getMembers() {
        return Collections.unmodifiableCollection(members.values());
    }

    public List<Loan> getLoans() {
        return Collections.unmodifiableList(loans);
    }
}
