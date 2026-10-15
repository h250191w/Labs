public class InvalidAgeException extends Exception {
    private static final long serialVersionUID = 1L;

    private final int age;

    public InvalidAgeException(int age) {
        super("Invalid age: " + age + " (must be between 0 and 120)");
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
