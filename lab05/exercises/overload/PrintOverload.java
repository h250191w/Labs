package exercises.overload;

import java.util.Arrays;

/** Basic 1: overload a print method for int, double, String and int[]. */
public class PrintOverload {

    static void print(int x)      { System.out.println("print(int):    " + x); }
    static void print(double x)   { System.out.println("print(double): " + x); }
    static void print(String x)   { System.out.println("print(String): " + x); }
    static void print(int[] x)    { System.out.println("print(int[]):  " + Arrays.toString(x)); }

    public static void main(String[] args) {
        print(42);
        print(3.14);
        print("hello");
        print(new int[]{1, 2, 3});
        print('A');          // char widens to int -> print(int) is chosen: prints 65
        print(7L);           // long widens to double -> print(double)
    }
}
