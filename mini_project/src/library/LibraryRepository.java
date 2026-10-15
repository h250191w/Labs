package library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
  Saves and loads a Library as three CSV files inside a data directory:
  <pre>
    items.csv    TYPE,id,title,extra          (extra = author for BOOK, minutes for DVD)
    members.csv  id,name
    loans.csv    memberId,itemId,borrowedOn   (item availability is derived from the loans)
  </pre>
 */
public class LibraryRepository {

    private final Path directory;

    public LibraryRepository(Path directory) {
        this.directory = directory;
    }

    public void save(Library library) throws IOException {
        Files.createDirectories(directory);

        List<String> itemLines = new ArrayList<>();
        for (LibraryItem item : library.getItems()) {
            List<String> fields = new ArrayList<>(List.of(item.typeName(), item.getId(), item.getTitle()));
            fields.addAll(item.extraFields());
            itemLines.add(CsvUtil.join(fields));
        }
        Files.write(directory.resolve("items.csv"), itemLines);

        List<String> memberLines = new ArrayList<>();
        for (Member member : library.getMembers()) {
            memberLines.add(CsvUtil.join(List.of(member.getId(), member.getName())));
        }
        Files.write(directory.resolve("members.csv"), memberLines);

        List<String> loanLines = new ArrayList<>();
        for (Loan loan : library.getLoans()) {
            loanLines.add(CsvUtil.join(List.of(
                    loan.getMember().getId(), loan.getItem().getId(), loan.getBorrowedOn().toString())));
        }
        Files.write(directory.resolve("loans.csv"), loanLines);
    }

    /** Loads saved data; a missing file simply means "nothing saved yet". */
    public Library load() throws IOException {
        Library library = new Library();

        for (String line : readLines("items.csv")) {
            library.addItem(parseItem(CsvUtil.split(line)));
        }
        for (String line : readLines("members.csv")) {
            List<String> f = CsvUtil.split(line);
            library.registerMember(new Member(f.get(0), f.get(1)));
        }
        for (String line : readLines("loans.csv")) {
            List<String> f = CsvUtil.split(line);
            library.restoreLoan(f.get(0), f.get(1), LocalDate.parse(f.get(2)));
        }
        return library;
    }

    private List<String> readLines(String fileName) throws IOException {
        Path file = directory.resolve(fileName);
        if (!Files.exists(file)) {
            return List.of();
        }
        return Files.readAllLines(file).stream().filter(line -> !line.isBlank()).toList();
    }

    /** Factory: builds the right subclass from the TYPE column. */
    private LibraryItem parseItem(List<String> f) {
        return switch (f.get(0)) {
            case "BOOK" -> new Book(f.get(1), f.get(2), f.get(3));
            case "DVD" -> new Dvd(f.get(1), f.get(2), Integer.parseInt(f.get(3)));
            default -> throw new IllegalArgumentException("Unknown item type: " + f.get(0));
        };
    }
}
