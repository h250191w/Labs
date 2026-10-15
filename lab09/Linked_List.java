import java.util.Iterator;
import java.util.NoSuchElementException;

/**
   Lab 9 - Exercise 4: a singly linked list.
*/
public class LinkedList<T> implements Iterable<T> {

    private static class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node<T> current = head;          // accesses the outer instance's field

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            T value = current.value;
            current = current.next;
            return value;
        }
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("alpha");
        list.add("beta");
        list.add("gamma");

        for (String item : list) {               
            System.out.println(item);
        }
        System.out.println("Size: " + list.size());
    }
}
