package exercises.animals;

public class AnimalDemo {
    public static void main(String[] args) {
        Animal[] farm = { new Cat(), new Cow(), new Duck(), new Animal() };
        for (Animal a : farm) {
            // The SAME call a.sound() produces a different result for each real object
            System.out.println(a.getClass().getSimpleName() + " says " + a.sound());
        }
    }
}
