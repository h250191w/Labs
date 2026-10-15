package exercises.shape;

/** Basic 1: abstract Shape with abstract area() and perimeter(). */
public abstract class Shape {
    public abstract double area();
    public abstract double perimeter();

    /** Concrete method shared by every shape. */
    public void describe() {
        System.out.printf("%-10s area=%8.2f perimeter=%8.2f%n", getClass().getSimpleName(), area(), perimeter());
    }
}
