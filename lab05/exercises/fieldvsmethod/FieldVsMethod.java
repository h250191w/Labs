package exercises.fieldvsmethod;

/**
 * Intermediate 2: output differs depending on whether a METHOD or a FIELD (or static method) is accessed
 * through a superclass reference.
 *
 * WHY:
 *  - Instance METHODS are looked up at run time from the real object (dynamic dispatch)    -> "Child"
 *  - FIELDS are resolved at compile time from the DECLARED type of the variable             -> "Parent"
 *    (the subclass field merely HIDES the superclass field; it does not override it)
 *  - STATIC methods are also resolved at compile time from the declared type                -> "Parent static"
 */
class Parent {
    String label = "Parent";
    String who() { return "Parent"; }
    static String id() { return "Parent static"; }
}

class Child extends Parent {
    String label = "Child";
    @Override String who() { return "Child"; }
    static String id() { return "Child static"; }
}

public class FieldVsMethod {
    public static void main(String[] args) {
        Parent p = new Child();      // declared type Parent, real type Child
        Child  c = new Child();      // declared type Child,  real type Child

        System.out.println("p.who()   = " + p.who());      // Child        (method: real object)
        System.out.println("p.label   = " + p.label);      // Parent       (field: declared type)
        System.out.println("p.id()    = " + p.id());       // Parent static(static: declared type)
        System.out.println("c.who()   = " + c.who());      // Child
        System.out.println("c.label   = " + c.label);      // Child
        System.out.println("c.id()    = " + c.id());       // Child static
        System.out.println("((Parent) c).label = " + ((Parent) c).label);   // Parent - casting changes the declared type
    }
}
