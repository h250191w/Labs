package library;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
  Console menu for the Library Management System.
  Usage: java -cp out library.LibraryApp [dataDirectory]   (default directory: data)
 */
public class LibraryApp {

    private final Library library;
    private final LibraryRepository repository;
    private final Scanner scanner = new Scanner(System.in);

    public LibraryApp(Library library, LibraryRepository repository) {
        this.library = library;
        this.repository = repository;
    }

    public static void main(String[] args) {
        LibraryRepository repository = new LibraryRepository(Path.of(args.length > 0 ? args[0] : "data"));
        try {
            Library library = repository.load();
            System.out.println("Loaded " + library.getItems().size() + " item(s), "
                    + library.getMembers().size() + " member(s), "
                    + library.getLoans().size() + " active loan(s).");
            new LibraryApp(library, repository).run();
        } catch (IOException | RuntimeException e) {
            System.err.println("Could not load library data: " + e.getMessage());
        }
    }

    private void run() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = prompt("Choose an option: ");
            try {
                switch (choice) {
                    case "1" -> addItem();
                    case "2" -> registerMember();
                    case "3" -> borrow();
                    case "4" -> returnItem();
                    case "5" -> listOverdue();
                    case "6" -> search();
                    case "7" -> listAll();
                    case "0" -> running = false;
                    default -> System.out.println("Unknown option, please try again.");
                }
            } catch (ItemNotAvailableException | MemberLimitExceededException e) {
                System.out.println("Cannot borrow: " + e.getMessage());
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        saveAndExit();
    }

    private void printMenu() {
        System.out.println("""

                ===== Library Management System =====
                 1. Add item (book or DVD)
                 2. Register member
                 3. Borrow an item
                 4. Return an item
                 5. List overdue items
                 6. Search by title
                 7. List all items and members
                 0. Save and exit""");
    }

    private void addItem() {
        String type = prompt("Type (book/dvd): ").toLowerCase();
        String id = prompt("Item id: ");
        String title = prompt("Title: ");

        LibraryItem item = switch (type) {
            case "book" -> new Book(id, title, prompt("Author: "));
            case "dvd" -> new Dvd(id, title, parseInt(prompt("Duration in minutes: ")));
            default -> throw new IllegalArgumentException("Type must be 'book' or 'dvd'");
        };
        library.addItem(item);
        System.out.println("Added: " + item);
    }

    private void registerMember() {
        Member member = new Member(prompt("Member id: "), prompt("Name: "));
        library.registerMember(member);
        System.out.println("Registered: " + member);
    }

    private void borrow() throws ItemNotAvailableException, MemberLimitExceededException {
        Loan loan = library.borrow(prompt("Member id: "), prompt("Item id: "), LocalDate.now());
        System.out.println("Borrowed: " + loan);
    }

    private void returnItem() {
        Loan loan = library.returnItem(prompt("Member id: "), prompt("Item id: "));
        LocalDate today = LocalDate.now();
        System.out.println("Returned: \"" + loan.getItem().getTitle() + "\"");
        if (loan.isOverdue(today)) {
            System.out.println("Note: it was " + loan.daysOverdue(today) + " day(s) overdue.");
        }
    }

    private void listOverdue() {
        String input = prompt("Check as of date (yyyy-mm-dd, blank for today): ");
        LocalDate asOf;
        try {
            asOf = input.isBlank() ? LocalDate.now() : LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Dates must look like 2026-10-31");
        }

        List<Loan> overdue = library.findOverdue(asOf);
        if (overdue.isEmpty()) {
            System.out.println("No overdue items as of " + asOf + ".");
            return;
        }
        System.out.println("Overdue as of " + asOf + ":");
        for (Loan loan : overdue) {
            System.out.println("  " + loan + " -> " + loan.daysOverdue(asOf) + " day(s) late");
        }
    }

    private void search() {
        List<LibraryItem> results = library.searchByTitle(prompt("Title contains: "));
        if (results.isEmpty()) {
            System.out.println("No matches.");
        } else {
            results.forEach(item -> System.out.println("  " + item));
        }
    }

    private void listAll() {
        System.out.println("Items:");
        library.getItems().forEach(item -> System.out.println("  " + item));
        System.out.println("Members:");
        library.getMembers().forEach(member -> System.out.println("  " + member));
    }

    private void saveAndExit() {
        try {
            repository.save(library);
            System.out.println("Data saved. Goodbye!");
        } catch (IOException e) {
            System.err.println("WARNING: could not save data: " + e.getMessage());
        }
    }

    private String prompt(String message) {
        System.out.print(message);
        return scanner.hasNextLine() ? scanner.nextLine().trim() : "0";   // end of input -> exit
    }

    private int parseInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("'" + text + "' is not a whole number");
        }
    }
}
