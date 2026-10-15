/**
  Lab 7 - Exercise 5: return inside try, print inside finally.
 
  Predicted output:
    finally block runs
    result = 1
 
  Why: when 'return 1' executes, the value is computed and saved, then control leaves the
  method - but a finally block ALWAYS runs on the way out (normal completion, return, or
  exception). The only things that stop it are System.exit() or the JVM dying.
  Caution: a 'return' inside finally would override the try's return value (bad practice).
 */
public class FinallyDemo {

    static int compute() {
        try {
            return 1;
        } finally {
            System.out.println("finally block runs");
        }
    }

    /** Shows the dangerous case: finally overrides the earlier return value. */
    @SuppressWarnings("finally")
    static int overridden() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }

    public static void main(String[] args) {
        int result = compute();
        System.out.println("result = " + result);
        System.out.println("return in finally overrides try: " + overridden());
    }
}
