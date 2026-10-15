/**
 * Lab 2 - Exercise 5: static factory methods with a private constructor.
 *
 * Why static factories can be clearer than overloaded constructors:
 * both fromCelsius(double) and fromFahrenheit(double) take a single double, so two
 * constructors with that signature would be illegal (same parameter list). Even where
 * overloading is possible, "new Temperature(25.0)" does not say which unit is meant,
 * whereas Temperature.fromCelsius(25.0) documents itself. Factories also have names,
 * can validate before creating, and may return cached or subclass instances.
 */
public class Temperature {
    private final double celsius;

    private Temperature(double celsius) {
        this.celsius = celsius;
    }

    public static Temperature fromCelsius(double celsius) {
        return new Temperature(celsius);
    }

    public static Temperature fromFahrenheit(double fahrenheit) {
        return new Temperature((fahrenheit - 32) * 5 / 9);
    }

    public double toCelsius() {
        return celsius;
    }

    public double toFahrenheit() {
        return celsius * 9 / 5 + 32;
    }

    @Override
    public String toString() {
        return String.format("%.1f C / %.1f F", toCelsius(), toFahrenheit());
    }

    public static void main(String[] args) {
        System.out.println(Temperature.fromCelsius(100));
        System.out.println(Temperature.fromFahrenheit(98.6));
        System.out.println(Temperature.fromFahrenheit(-40));
    }
}
