
public class Circle {
    private static int count = 0;

    private final double radius;

    public Circle() {
        this(1.0);                 // default radius is 1.0
    }

    public Circle(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.radius = radius;
        count++;
    }

    public double getRadius() {
        return radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return String.format("Circle[radius=%.2f, area=%.2f]", radius, area());
    }

    public static void main(String[] args) {
        System.out.println("Circles created: " + Circle.getCount());
        Circle unit = new Circle();
        Circle big = new Circle(5.5);
        System.out.println(unit);
        System.out.println(big);
        System.out.println("Circles created: " + Circle.getCount());
    }
}
