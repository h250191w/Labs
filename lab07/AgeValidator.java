public class AgeValidator {
    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 120;

    public static void validateAge(int age) throws InvalidAgeException {
        if (age < MIN_AGE || age > MAX_AGE) {
            throw new InvalidAgeException(age);
        }
    }

    public static void main(String[] args) {
        int[] ages = {25, -3, 121, 0, 120};
        for (int age : ages) {
            try {
                validateAge(age);
                System.out.println(age + " is valid");
            } catch (InvalidAgeException e) {
                System.out.println("Rejected: " + e.getMessage());
            }
        }
    }
}
