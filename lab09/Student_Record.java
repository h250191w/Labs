/**
  Lab 9 - Exercise 2: records give a constructor, accessors, equals, hashCode and toString for free, and are immutable.
 */
public class StudentRecordDemo {

    record Student(String name, int age) { }

    public static void main(String[] args) {
        Student a = new Student("Tendai", 20);
        Student b = new Student("Tendai", 20);
        Student c = new Student("Rudo", 22);

        System.out.println("Printed:           " + a);
        System.out.println("a.equals(b):       " + a.equals(b));      // same component values
        System.out.println("a == b:            " + (a == b));         // different objects
        System.out.println("a.equals(c):       " + a.equals(c));
        System.out.println("Same hash codes?   " + (a.hashCode() == b.hashCode()));
        System.out.println("Component name():  " + a.name());
        System.out.println("Component age():   " + a.age());
    }
}
