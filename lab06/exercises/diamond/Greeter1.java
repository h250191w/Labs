package exercises.diamond;

public interface Greeter1 {
    default void hello() { System.out.println("Hello from Greeter1"); }
}
