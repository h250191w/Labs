import java.util.Arrays;

/**
  Lab 8 - Exercise 5: generic bubble sort.

 */
public class BubbleSort {

    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        boolean swapped;
        for (int pass = 0; pass < arr.length - 1; pass++) {
            swapped = false;
            for (int i = 0; i < arr.length - 1 - pass; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;                     
            }
        }
    }

    public static void main(String[] args) {
        Integer[] numbers = {42, 7, 19, 3, 88, 1};
        String[] words = {"mango", "apple", "cherry", "banana"};

        bubbleSort(numbers);
        bubbleSort(words);

        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(words));

        // int[] primitives = {3, 1, 2};
        // bubbleSort(primitives);      // compile error: int[] cannot be inferred as T[]
    }
}
