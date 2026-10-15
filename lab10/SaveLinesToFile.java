import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
  Lab 10 - Exercise 1: save five lines typed by the user to a file, then read them back.
 */
public class SaveLinesToFile {

    private static final int LINE_COUNT = 5;

    public static void main(String[] args) {
        Path file = Path.of("typed_lines.txt");
        List<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = 1; i <= LINE_COUNT && scanner.hasNextLine(); i++) {
                System.out.print("Line " + i + ": ");
                lines.add(scanner.nextLine());
            }
        }

        try {
            Files.write(file, lines);
            System.out.println("\nSaved " + lines.size() + " line(s) to " + file.toAbsolutePath());

            System.out.println("Reading back:");
            List<String> loaded = Files.readAllLines(file);
            for (int i = 0; i < loaded.size(); i++) {
                System.out.println((i + 1) + ": " + loaded.get(i));
            }
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
    }
}
