/**
 Lab 4 - Exercise 4: why "class Dog extends Animal" fails when Animal only has Animal(String name).
 *
 * Every subclass constructor must begin with a call to a superclass constructor. If none is
   written, the compiler silently inserts super() - a call to the NO-ARGUMENT constructor.
 * Animal declares only Animal(String), so the compiler removes its default no-arg constructor
   and the inserted super() has nothing to call:
 
 *     error: constructor Animal in class Animal cannot be applied to given types;

 * The broken version (kept in a comment so this file compiles):

 *     class Dog extends Animal {
 *         Dog() { }                  // implicit super() -> compile error
 *     }

 * Fixes: call the existing constructor explicitly with super(name), or give Animal a
   no-argument constructor as well.
 */
class Animal {
    private final String name;

    Animal(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);               // the fix: explicit call to Animal(String)
    }

    String speak() {
        return getName() + " says Woof";
    }
}

public class MissingSuperDemo {
    public static void main(String[] args) {
        System.out.println(new Dog("Rex").speak());
    }
}
