
public class Rectangle {
    double width;
    double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return String.format("Rectangle[%.1f x %.1f, area=%.1f, perimeter=%.1f]",
                width, height, area(), perimeter());
    }

    public static void main(String[] args) {
        Rectangle[] rectangles = {
            new Rectangle(2, 3),
            new Rectangle(5, 4),
            new Rectangle(10, 1),
            new Rectangle(7, 6),
            new Rectangle(3, 3)
        };

        Rectangle largest = rectangles[0];
        for (Rectangle r : rectangles) {
            if (r.area() > largest.area()) {
                largest = r;
            }
        }
        System.out.println("Largest rectangle: " + largest);
    }
}
