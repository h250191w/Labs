import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 Lab 8 - Exercise 2: a Set rejects duplicates. LinkedHashSet also keeps first-seen order.
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        List<String> names = List.of("Denzel", "Mary", "Edric", "Chanty", "Elton", "Chipo", "Elton");

        Set<String> unique = new LinkedHashSet<>(names);

        System.out.println("Original: " + names);
        System.out.println("Unique:   " + new ArrayList<>(unique));
    }
}
