/**
 * Lab 7 - Exercise 2: accessing an array element and catching ArrayIndexOutOfBoundsException.
 */
public class ArrayIndexOutOfBoundsException {

    static void printElement(int[] data, int index) {
        try {
            System.out.println("data[" + index + "] = " + data[index]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index " + index + " is invalid; valid range is 0 to " + (data.length - 1));
        }
    }

    public static void main(String[] args) {
        int[] data = {10, 20, 30};
        printElement(data, 1);
        printElement(data, 3);
        printElement(data, -1);
    }
}
