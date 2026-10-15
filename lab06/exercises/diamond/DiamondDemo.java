package exercises.diamond;

/**
 * Challenge: two interfaces both declare default void hello().
 *
 * THE RULE: if a class inherits two default methods with the same signature from unrelated interfaces,
 * the compiler refuses to guess ("types Greeter1 and Greeter2 are incompatible; class inherits unrelated
 * defaults for hello()"). The class MUST override hello() itself. Inside that override it can choose which
 * parent version to reuse with  InterfaceName.super.hello()  (or provide completely new behaviour).
 * Rules of precedence: (1) a class method always wins over an interface default; (2) a more specific
 * interface wins over a less specific one; (3) otherwise the conflict must be resolved manually, as here.
 */
public class DiamondDemo implements Greeter1, Greeter2 {

    @Override
    public void hello() {
        Greeter1.super.hello();      // explicitly pick the Greeter1 version
        Greeter2.super.hello();      // ... and also call the Greeter2 version
        System.out.println("Hello from DiamondDemo (conflict resolved)");
    }

    public static void main(String[] args) {
        new DiamondDemo().hello();
    }
}
