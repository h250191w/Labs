package exercises.account;

public class CurrentAccount extends Account {
    public CurrentAccount(String number, double openingBalance) { super(number, openingBalance); }

    /** Current: flat 10.00 maintenance fee. */
    @Override public double monthlyFee() { return 10.00; }
}
