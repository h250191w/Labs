import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
  Lab 10 - Exercise 5: load learners from learners.csv, group them by grade band with
  Collectors.groupingBy, and write one report file per band into the "reports" folder.
 
  Bands by GPA:  A = 3.5 and above,  B = 3.0 to 3.49,  C = below 3.0
  CSV format:    name,roll,gpa
 */
public class LearnerReports {

    record Learner(String name, int roll, double gpa) {

        static Learner fromCsv(String line) {
            String[] parts = line.split(",");
            return new Learner(parts[0].trim(), Integer.parseInt(parts[1].trim()),
                    Double.parseDouble(parts[2].trim()));
        }

        String band() {
            if (gpa >= 3.5) return "A";
            if (gpa >= 3.0) return "B";
            return "C";
        }
    }

    static List<Learner> load(Path csv) throws IOException {
        return Files.readAllLines(csv).stream()
                .filter(line -> !line.isBlank())
                .map(Learner::fromCsv)
                .toList();
    }

    static void writeReports(Map<String, List<Learner>> byBand, Path outputDir) throws IOException {
        Files.createDirectories(outputDir);

        for (Map.Entry<String, List<Learner>> entry : byBand.entrySet()) {
            List<String> report = new ArrayList<>();
            report.add("Band " + entry.getKey() + " report (" + entry.getValue().size() + " learner(s))");
            report.add("-".repeat(40));

            entry.getValue().stream()
                    .sorted(Comparator.comparingDouble(Learner::gpa).reversed())
                    .map(l -> String.format("%-12s roll %-3d GPA %.2f", l.name(), l.roll(), l.gpa()))
                    .forEach(report::add);

            Path file = outputDir.resolve("band_" + entry.getKey() + ".txt");
            Files.write(file, report);
            System.out.println("Wrote " + file + " (" + entry.getValue().size() + " learners)");
        }
    }

    public static void main(String[] args) {
        Path csv = Path.of(args.length > 0 ? args[0] : "learners.csv");
        try {
            List<Learner> learners = load(csv);

            Map<String, List<Learner>> byBand = learners.stream()
                    .collect(Collectors.groupingBy(Learner::band, TreeMap::new, Collectors.toList()));

            writeReports(byBand, Path.of("reports"));
        } catch (IOException e) {
            System.err.println("Could not process " + csv + ": " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Malformed data in " + csv + ": " + e.getMessage());
        }
    }
}
