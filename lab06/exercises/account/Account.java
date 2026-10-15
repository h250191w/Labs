package exercises.account;

/** Intermediate 1: abstract Account with concrete deposit() and abstract monthlyFee(). */
public abstract class Account {
    protected double balance;
    private final String number;

    protected Account(String number, double openingBalance) {
        this.number = number;
        this.balance = openingBalance;
    }

    public String getNumber()  { return number; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
    }

    /** Each account type decides its own monthly fee. */
    public abstract double monthlyFee();

    public void applyMonthlyFee() {
        balance -= monthlyFee();
    }
}
