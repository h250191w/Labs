package exercises.payment;

/** Challenge: payment hierarchy. Version 2 (polymorphic) adds an abstract process() here. */
public abstract class Payment {
    protected final double amount;

    protected Payment(double amount) { this.amount = amount; }

    public double getAmount() { return amount; }

    /** DESIGN 2: each subclass knows how to process itself. */
    public abstract String process();
}
