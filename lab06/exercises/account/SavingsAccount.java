package exercises.account;

public class SavingsAccount extends Account {
    public SavingsAccount(String number, double openingBalance) { super(number, openingBalance); }

    /** Savings: free if the balance is at least 1000, otherwise a flat 2.00. */
    @Override public double monthlyFee() { return balance >= 1000 ? 0.0 : 2.00; }
}
