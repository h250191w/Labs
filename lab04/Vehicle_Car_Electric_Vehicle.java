public class Vehicle {
    private final String make;

    public Vehicle(String make) {
        this.make = make;
        System.out.println("1. Vehicle constructor: " + make);
    }

    public String getMake() {
        return make;
    }
}

public class Car extends Vehicle {
    private final int doors;

    public Car(String make, int doors) {
        super(make);                          // always runs first
        this.doors = doors;
        System.out.println("2. Car constructor: " + doors + " doors");
    }

    public int getDoors() {
        return doors;
    }
}

public class ElectricCar extends Car {
    private final int batteryKwh;

    public ElectricCar(String make, int doors, int batteryKwh) {
        super(make, doors);
        this.batteryKwh = batteryKwh;
        System.out.println("3. ElectricCar constructor: " + batteryKwh + " kWh battery");
    }

    public int getBatteryKwh() {
        return batteryKwh;
    }
}

/*
Vehicle() executes first.
Car() executes second.
ElectricCar() executes last. */