package exercises.shape;

public class ShapeDemo {
    public static void main(String[] args) {
        // Shape s = new Shape();   // COMPILE ERROR: Shape is abstract
        Shape[] shapes = { new Circle(3), new Rectangle(4, 6), new Square(5) };
        for (Shape s : shapes) {
            s.describe();
        }
    }
}
