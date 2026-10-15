package exercises.flyable;

public class FlyableDemo {
    public static void main(String[] args) {
        // Bird and Aeroplane share NO superclass, only the Flyable capability
        Flyable[] things = { new Bird(), new Aeroplane() };
        for (Flyable f : things) {
            f.fly();
        }
    }
}
