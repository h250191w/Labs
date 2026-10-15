package exercises.payment;

/** Added LATER, to show how each design copes with a new payment type. */
public class CashPayment extends Payment {
    public CashPayment(double amount) { super(amount); }

    @Override
    public String process() {
        return String.format("Received %.2f in cash", amount);
    }
}
