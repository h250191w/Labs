import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 Lab 8 - Exercise 1: sum, maximum and reverse order of 10 integers.
 */
public class ListStatistics {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(14, 7, 92, 3, 58, 21, 66, 40, 9, 75));

        int sum = 0;
        for (int n : numbers) {
            sum += n;
        }

        System.out.println("Numbers: " + numbers);
        System.out.println("Sum:     " + sum);
        System.out.println("Maximum: " + Collections.max(numbers));

        List<Integer> reversed = new ArrayList<>(numbers);
        Collections.reverse(reversed);
        System.out.println("Reverse: " + reversed);
    }
}
