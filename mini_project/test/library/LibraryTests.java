package library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

/**
 * Dependency-free test runner (no JUnit needed).
 * Run: java -cp out library.LibraryTests      Exit code 1 means at least one test failed.
 */
public class LibraryTests {

    private static int passed = 0;
    private static int failed = 0;

    private static final LocalDate TODAY = LocalDate.of(2026, 10, 15);

    public static void main(String[] args) throws Exception {
        borrowMarksItemUnavailable();
        borrowingTwiceFails();
        memberLimitIsEnforced();
        returnMakesItemAvailableAgain();
        returningUnborrowedItemFails();
        loanPeriodDiffersByType();
        overdueDetection();
        searchIsCaseInsensitiveAndSorted();
        validationRejectsBadInput();
        duplicateIdsRejected();
        csvHandlesCommasAndQuotes();
        persistenceRoundTrip();

        System.out.printf("%n%d passed, %d failed%n", passed, failed);
        if (failed > 0) {
            System.exit(1);
        }
    }

    // ---------- fixtures ----------

    private static Library sampleLibrary() {
        Library lib = new Library();
        lib.addItem(new Book("B1", "Clean Code", "Robert Martin"));
        lib.addItem(new Book("B2", "Effective Java", "Joshua Bloch"));
        lib.addItem(new Book("B3", "Java Concurrency in Practice", "Brian Goetz"));
        lib.addItem(new Book("B4", "Refactoring", "Martin Fowler"));
        lib.addItem(new Dvd("D1", "The Matrix", 136));
        lib.registerMember(new Member("M1", "Tendai"));
        lib.registerMember(new Member("M2", "Rudo"));
        return lib;
    }

    // ---------- tests ----------

    private static void borrowMarksItemUnavailable() throws Exception {
        Library lib = sampleLibrary();
        lib.borrow("M1", "B1", TODAY);
        check("borrow marks item unavailable", !lib.findItem("B1").isAvailable());
        check("borrow records loan on member", lib.findMember("M1").getActiveLoans().size() == 1);
    }

    private static void borrowingTwiceFails() throws Exception {
        Library lib = sampleLibrary();
        lib.borrow("M1", "B1", TODAY);
        check("second borrow throws ItemNotAvailableException",
                throwsChecked(() -> lib.borrow("M2", "B1", TODAY), ItemNotAvailableException.class));
    }

    private static void memberLimitIsEnforced() throws Exception {
        Library lib = sampleLibrary();
        lib.borrow("M1", "B1", TODAY);
        lib.borrow("M1", "B2", TODAY);
        lib.borrow("M1", "B3", TODAY);
        check("4th loan throws MemberLimitExceededException",
                throwsChecked(() -> lib.borrow("M1", "B4", TODAY), MemberLimitExceededException.class));
        check("rejected item stays available", lib.findItem("B4").isAvailable());
    }

    private static void returnMakesItemAvailableAgain() throws Exception {
        Library lib = sampleLibrary();
        lib.borrow("M1", "B1", TODAY);
        lib.returnItem("M1", "B1");
        check("returned item is available", lib.findItem("B1").isAvailable());
        check("member has no loans after return", lib.findMember("M1").getActiveLoans().isEmpty());
        check("library has no active loans", lib.getLoans().isEmpty());
    }

    private static void returningUnborrowedItemFails() {
        Library lib = sampleLibrary();
        check("returning an item never borrowed throws",
                throwsUnchecked(() -> lib.returnItem("M1", "B1"), IllegalArgumentException.class));
    }

    private static void loanPeriodDiffersByType() throws Exception {
        Library lib = sampleLibrary();
        Loan bookLoan = lib.borrow("M1", "B1", TODAY);
        Loan dvdLoan = lib.borrow("M1", "D1", TODAY);
        check("book due after 21 days", bookLoan.getDueDate().equals(TODAY.plusDays(21)));
        check("dvd due after 7 days", dvdLoan.getDueDate().equals(TODAY.plusDays(7)));
    }

    private static void overdueDetection() throws Exception {
        Library lib = sampleLibrary();
        lib.borrow("M1", "D1", TODAY);                 // due in 7 days
        lib.borrow("M2", "B1", TODAY);                 // due in 21 days
        check("nothing overdue on the due date", lib.findOverdue(TODAY.plusDays(7)).isEmpty());
        List<Loan> overdue = lib.findOverdue(TODAY.plusDays(8));
        check("only the DVD is overdue after 8 days",
                overdue.size() == 1 && overdue.get(0).getItem().getId().equals("D1"));
        check("days overdue is computed", overdue.get(0).daysOverdue(TODAY.plusDays(10)) == 3);
    }

    private static void searchIsCaseInsensitiveAndSorted() {
        Library lib = sampleLibrary();
        List<LibraryItem> results = lib.searchByTitle("JAVA");
        check("search finds both Java titles", results.size() == 2);
        check("search results sorted by title",
                results.get(0).getTitle().equals("Effective Java")
                        && results.get(1).getTitle().equals("Java Concurrency in Practice"));
        check("search with no match is empty", lib.searchByTitle("zzz").isEmpty());
    }

    private static void validationRejectsBadInput() {
        check("blank title rejected", throwsUnchecked(() -> new Book("X", " ", "A"), IllegalArgumentException.class));
        check("blank author rejected", throwsUnchecked(() -> new Book("X", "T", ""), IllegalArgumentException.class));
        check("non-positive duration rejected", throwsUnchecked(() -> new Dvd("X", "T", 0), IllegalArgumentException.class));
        check("blank member name rejected", throwsUnchecked(() -> new Member("M", ""), IllegalArgumentException.class));
    }

    private static void duplicateIdsRejected() {
        Library lib = sampleLibrary();
        check("duplicate item id rejected",
                throwsUnchecked(() -> lib.addItem(new Dvd("B1", "Clash", 90)), IllegalArgumentException.class));
        check("duplicate member id rejected",
                throwsUnchecked(() -> lib.registerMember(new Member("M1", "Clash")), IllegalArgumentException.class));
        check("unknown member rejected",
                throwsChecked(() -> lib.borrow("NOPE", "B1", TODAY), IllegalArgumentException.class));
    }

    private static void csvHandlesCommasAndQuotes() {
        List<String> fields = List.of("BOOK", "B9", "Java, \"The\" Good Parts", "Douglas Crockford");
        List<String> parsed = CsvUtil.split(CsvUtil.join(fields));
        check("csv round trip keeps commas and quotes", parsed.equals(fields));
    }

    private static void persistenceRoundTrip() throws IOException, Exception {
        Path dir = Files.createTempDirectory("library-test");
        try {
            Library original = sampleLibrary();
            original.addItem(new Book("B5", "Head First Java, 3rd Edition", "Kathy Sierra"));
            original.borrow("M1", "B1", LocalDate.of(2026, 9, 1));
            original.borrow("M2", "D1", LocalDate.of(2026, 10, 10));

            LibraryRepository repository = new LibraryRepository(dir);
            repository.save(original);
            Library reloaded = repository.load();

            check("reload keeps all items", reloaded.getItems().size() == 6);
            check("reload keeps all members", reloaded.getMembers().size() == 2);
            check("reload keeps active loans", reloaded.getLoans().size() == 2);
            check("reload derives availability from loans",
                    !reloaded.findItem("B1").isAvailable() && reloaded.findItem("B2").isAvailable());
            check("reload restores item subtype",
                    reloaded.findItem("D1") instanceof Dvd && reloaded.findItem("B5") instanceof Book);
            check("reload keeps titles containing commas",
                    reloaded.findItem("B5").getTitle().equals("Head First Java, 3rd Edition"));
            check("reload keeps borrow dates",
                    reloaded.findOverdue(LocalDate.of(2026, 10, 15)).size() == 1);
            check("reload restores member loan counts", reloaded.findMember("M1").getActiveLoans().size() == 1);
        } finally {
            try (var files = Files.list(dir)) {
                for (Path file : files.toList()) {
                    Files.deleteIfExists(file);
                }
            }
            Files.deleteIfExists(dir);
        }
    }

    // ---------- tiny assertion helpers ----------

    @FunctionalInterface
    private interface ThrowingAction {
        void run() throws Exception;
    }

    private static boolean throwsChecked(ThrowingAction action, Class<? extends Exception> expected) {
        try {
            action.run();
            return false;
        } catch (Exception e) {
            return expected.isInstance(e);
        }
    }

    private static boolean throwsUnchecked(Runnable action, Class<? extends RuntimeException> expected) {
        try {
            action.run();
            return false;
        } catch (RuntimeException e) {
            return expected.isInstance(e);
        }
    }

    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("PASS  " + name);
        } else {
            failed++;
            System.out.println("FAIL  " + name);
        }
    }
}
