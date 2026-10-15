import java.time.Year;


public class Car {
    private static final int ANTIQUE_AGE_YEARS = 25;

    private final String brand;
    private final int year;
    private double mileage;

    public Car(String brand, int year, double mileage) {
        this.brand = brand;
        this.year = year;
        this.mileage = mileage;
    }

   
    public boolean isAntique() {
        return Year.now().getValue() - year > ANTIQUE_AGE_YEARS;
    }

    public void display() {
        System.out.printf("%s (%d) - %.1f km - antique: %b%n", brand, year, mileage, isAntique());
    }

    public static void main(String[] args) {
        Car[] cars = {
            new Car("Toyota", 2018, 85_000.5),
            new Car("Mercedes-Benz", 1985, 240_300),
            new Car("Honda", 2001, 150_000)
        };
        for (Car car : cars) {
            car.display();
        }
    }
}
