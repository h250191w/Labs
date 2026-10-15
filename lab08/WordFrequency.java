import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
  Lab 8 - Exercise 4: word frequencies from a paragraph, sorted by frequency (descending).
 
 */
public class WordFrequency {

    private static final String SAMPLE =
            "The quick brown fox jumps over the lazy dog. The dog barks and the fox runs away; "
            + "the fox is quick, the dog is lazy.";

    static Map<String, Integer> countWords(String text) {
        Map<String, Integer> counts = new TreeMap<>();        // TreeMap: alphabetical for ties
        for (String word : text.toLowerCase().split("[^a-z']+")) {
            if (!word.isEmpty()) {
                counts.merge(word, 1, Integer::sum);
            }
        }
        return counts;
    }

    public static void main(String[] args) {
        System.out.println("Enter a paragraph (or press Enter for a sample):");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.hasNextLine() ? scanner.nextLine() : "";
        if (text.isBlank()) {
            text = SAMPLE;
            System.out.println("Using sample: " + SAMPLE);
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(countWords(text).entrySet());
        // Stable sort: equal counts keep the alphabetical order from the TreeMap
        entries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        System.out.println("\nWord frequencies:");
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.printf("%-8s %d%n", entry.getKey(), entry.getValue());
        }
    }
}
