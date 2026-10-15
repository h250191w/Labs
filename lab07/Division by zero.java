import java.util.InputMismatchException;
import java.util.Scanner;
public class SafeDivision {

    private static int readInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();               // throws InputMismatchException for non-numbers
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int numerator = readInt(scanner, "Enter the numerator: ");
            int denominator = readInt(scanner, "Enter the denominator: ");
            System.out.printf("%d / %d = %d%n", numerator, denominator, numerator / denominator);
        } catch (InputMismatchException e) {
            System.out.println("\nError: please enter whole numbers only.");
        } catch (ArithmeticException e) {
            System.out.println("Error: cannot divide by zero.");
        }
    }
}
