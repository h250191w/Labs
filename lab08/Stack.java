import java.util.ArrayList;
import java.util.List;

/**
  Lab 8 - Exercise 3: generic Stack<T> backed by an ArrayList<T> (last in, first out).
 */
public class Stack<T> {
    private final List<T> items = new ArrayList<>();

    public void push(T item) {
        items.add(item);
    }

    public T pop() {
        requireNotEmpty();
        return items.remove(items.size() - 1);
    }

    public T peek() {
        requireNotEmpty();
        return items.get(items.size() - 1);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }

    private void requireNotEmpty() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
    }

    public static void main(String[] args) {
        Stack<String> words = new Stack<>();
        words.push("one");
        words.push("two");
        words.push("three");
        System.out.println("Peek: " + words.peek() + ", size: " + words.size());

        while (!words.isEmpty()) {
            System.out.println("Pop: " + words.pop());
        }

        Stack<Integer> numbers = new Stack<>();     
        numbers.push(10);
        numbers.push(20);
        int top = numbers.pop();                     
        System.out.println("Popped integer: " + top);

        try {
            numbers.pop();
            numbers.pop();
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
