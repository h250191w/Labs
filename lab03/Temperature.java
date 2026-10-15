public class Temperature {
    private static final double ABSOLUTE_ZERO_KELVIN = 0.0;
    private static final double CELSIUS_OFFSET = 273.15;

    private double kelvin;

    private Temperature(double kelvin) {
        setKelvin(kelvin);
    }

    public static Temperature ofCelsius(double celsius) {
        return new Temperature(celsius + CELSIUS_OFFSET);
    }

    public static Temperature ofFahrenheit(double fahrenheit) {
        return new Temperature((fahrenheit - 32) * 5 / 9 + CELSIUS_OFFSET);
    }

    private final void setKelvin(double kelvin) {
        if (kelvin < ABSOLUTE_ZERO_KELVIN) {
            throw new IllegalArgumentException("Temperature below absolute zero is impossible");
        }
        this.kelvin = kelvin;
    }

    public double getCelsius() {
        return kelvin - CELSIUS_OFFSET;
    }

    public double getFahrenheit() {
        return getCelsius() * 9 / 5 + 32;
    }

    public void setCelsius(double celsius) {
        setKelvin(celsius + CELSIUS_OFFSET);
    }

    @Override
    public String toString() {
        return String.format("%.2f C / %.2f F", getCelsius(), getFahrenheit());
    }

    public static void main(String[] args) {
        Temperature t = Temperature.ofCelsius(25);
        System.out.println(t);

        t.setCelsius(-273.15);
        System.out.println("Absolute zero: " + t);

        try {
            t.setCelsius(-300);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println("Unchanged: " + t);
    }
}
