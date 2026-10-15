
public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /** Inherited by every subclass; written only once. */
    public void display() {
        System.out.printf("Name: %s, Age: %d%n", name, age);
    }
}
