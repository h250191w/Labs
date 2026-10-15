package exercises.diamond;

public interface Greeter2 {
    default void hello() { System.out.println("Hello from Greeter2"); }
}
