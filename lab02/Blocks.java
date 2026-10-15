/**
 * Lab 2 - Exercise 4: static and instance initialiser blocks.
 *
 * Predicted output:
 *   Static block 1        (once, when the class is first loaded, in textual order)
 *   Static block 2
 *   main starts
 *   Instance block 1      (before the constructor body, every time an object is created)
 *   Instance block 2
 *   Constructor
 *   Instance block 1      (second object: instance blocks run again, static blocks do not)
 *   Instance block 2
 *   Constructor
 */
public class InitBlocksDemo {

    static {
        System.out.println("Static block 1");
    }

    {
        System.out.println("Instance block 1");
    }

    static {
        System.out.println("Static block 2");
    }

    {
        System.out.println("Instance block 2");
    }

    public InitBlocksDemo() {
        System.out.println("Constructor");
    }

    public static void main(String[] args) {
        System.out.println("main starts");
        new InitBlocksDemo();
        new InitBlocksDemo();
    }
}
