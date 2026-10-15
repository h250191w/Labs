import java.util.ArrayList;
import java.util.List;

/**
  Lab 4 - Exercise 5: composition instead of inheritance.
 
 * Stack extends ArrayList" wrongly exposes everything ArrayList offers: add(index, e),
   remove(index), set(), get(index), sort() and so on. A user could insert into the middle of
   the "stack" and break the last-in-first-out guarantee. It also claims Stack IS-A List,
   which is false. Composition (a private ArrayList field) exposes only the operations a stack
   really has: push, pop, peek, isEmpty, size.
 */
@SuppressWarnings("serial")
class BadStack<T> extends ArrayList<T> {
    void push(T item) {
        add(item);
    }

    T pop() {
        return remove(size() - 1);
    }
}

class Stack<T> {
    private final List<T> items = new ArrayList<>();

    void push(T item) {
        items.add(item);
    }

    T pop() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return items.remove(items.size() - 1);
    }

    T peek() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return items.get(items.size() - 1);
    }

    boolean isEmpty() {
        return items.isEmpty();
    }

    int size() {
        return items.size();
    }
}

public class StackDemo {
    public static void main(String[] args) {
        BadStack<String> bad = new BadStack<>();
        bad.push("A");
        bad.push("B");
        bad.add(0, "SNEAKY");            // allowed: breaks the LIFO rule
        System.out.println("Inheritance version leaks list methods: " + bad);

        Stack<String> good = new Stack<>();
        good.push("A");
        good.push("B");
        // good.add(0, "SNEAKY");        // would not compile: Stack has no add method
        System.out.println("Composition version peek: " + good.peek());
        System.out.println("Pop: " + good.pop() + ", then " + good.pop());
        System.out.println("Empty? " + good.isEmpty());
    }
}
