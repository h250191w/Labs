/**
 * Lab 1 - Exercise 4: reference aliasing.
 *
 * Prediction: r1.width prints 99.0.
 * Explanation: 'Rectangle r2 = r1;' copies the REFERENCE, not the object. Both variables
 * point at the same Rectangle on the heap, so a change made through r2 is visible through r1.
 * To get an independent object a second 'new Rectangle(...)' (or a copy constructor) is required.
 */
public class AliasingDemo {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(10, 5);
        Rectangle r2 = r1;          // alias: same object
        r2.width = 99;

        System.out.println("r1.width = " + r1.width);
        System.out.println("Same object? " + (r1 == r2));

        Rectangle r3 = new Rectangle(r1.width, r1.height);   // independent copy
        r3.width = 1;
        System.out.println("After changing the copy r3, r1.width = " + r1.width);
    }
}
