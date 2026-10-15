package exercises.payment;

public class PaymentDemo {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        Payment[] payments = {
            new CardPayment(120.50, "4417"),
            new MobileMoneyPayment(30, "+263 77 123 4567"),
            new CashPayment(15)
        };

        for (Payment p : payments) {
            System.out.println("--- " + p.getClass().getSimpleName() + " ---");
            try {
                System.out.println("V1 (instanceof): " + processor.payV1(p));
            } catch (IllegalArgumentException e) {
                System.out.println("V1 (instanceof): FAILED -> " + e.getMessage());
            }
            System.out.println("V2 (polymorphic): " + processor.payV2(p));
        }

        /*
         * COMPARISON
         *  V1 instanceof chain: all the logic is in one place and the Payment classes stay "dumb" data holders,
         *   BUT every new payment type means editing the processor (and every other chain like it). Forgetting one
         *   fails at run time, as CashPayment shows. It violates the open/closed principle.
         *  V2 polymorphism: adding CashPayment needed NO change to the processor. The compiler forces every concrete
         *   subclass to implement process(), so a missing case is a compile error, not a run-time surprise.
         *   Trade-off: the processing logic is spread across the subclasses.
         *  Use instanceof for closed hierarchies you don't control; prefer polymorphism when you own the hierarchy
         *   and expect new types.
         */
    }
}
