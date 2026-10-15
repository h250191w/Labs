import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
  Lab 10 - Exercise 2: a Predicate and removeIf.
 */
public class RemoveEvens {
    public static void main(String[] args) {
        
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Predicate<Integer> isEven = n -> n % 2 == 0;

        System.out.println("Before: " + numbers);
        numbers.removeIf(isEven);
        System.out.println("After:  " + numbers);
    }
}
