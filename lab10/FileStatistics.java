import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
  Lab 10 - Exercise 3: count the lines, words and characters of a text file.
 */
public class FileStatistics {

    public static void main(String[] args) {
        try {
            Path file = args.length > 0 ? Path.of(args[0]) : createSampleFile();

            List<String> lines = Files.readAllLines(file);
            long words = lines.stream()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .mapToLong(line -> line.split("\\s+").length)
                    .sum();
            long characters = Files.readString(file).length();

            System.out.println("File:       " + file);
            System.out.println("Lines:      " + lines.size());
            System.out.println("Words:      " + words);
            System.out.println("Characters: " + characters);
        } catch (IOException e) {
            System.err.println("Could not read file: " + e.getMessage());
        }
    }

    private static Path createSampleFile() throws IOException {
        Path sample = Path.of("sample.txt");
        Files.write(sample, List.of(
                "Object-oriented programming models the world as objects.",
                "",
                "Java is a popular object-oriented language."));
        return sample;
    }
}
