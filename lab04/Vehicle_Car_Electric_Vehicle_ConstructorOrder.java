/**
 * Lab 4 - Exercise 3: multilevel inheritance (Vehicle -> Car -> ElectricCar).
 * Constructors run from the top of the hierarchy down, so the output is numbered 1, 2, 3.
 */
public class ConstructorOrderDemo {
    public static void main(String[] args) {
        System.out.println("Creating an ElectricCar...");
        ElectricCar car = new ElectricCar("Nissan Leaf", 5, 40);
        System.out.println("Created: " + car.getMake() + ", " + car.getDoors() + " doors, "
                + car.getBatteryKwh() + " kWh");
    }
}
