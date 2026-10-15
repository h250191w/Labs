/**
  Lab 7 - Exercise 4: BankAccount whose withdraw() throws InsufficientFundsException.
 
  Trade-offs versus returning a boolean:
   - The failure cannot be silently ignored: a checked exception forces the caller to handle it
     (a returned false is easily forgotten).
   - The exception carries details (balance, shortfall, message), not just "it failed".
   - The happy path stays clean; error handling is separated from normal logic.
   - Callers must write try/catch or declare throws, which adds boilerplate.
   - Insufficient funds is an EXPECTED business outcome, not an exceptional bug, so some
     designers prefer a result object or boolean for very frequent, routine failures.
   - Exceptions are slower than a simple return when thrown in a tight loop.
 */

public class BankAccount {
    private final String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.balance = openingBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }
        if (amount > balance) {
            throw new InsufficientFundsException(balance, amount);
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return String.format("%s balance: %.2f", accountNumber, balance);
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount("ACC-001", 500);
        try {
            account.withdraw(200);
            System.out.println("Withdrew 200 -> " + account);
            account.withdraw(1000);
            System.out.println("This line is never reached");
        } catch (InsufficientFundsException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
            System.out.printf("Short by: %.2f%n", e.getShortfall());
        }
        System.out.println("Final: " + account);
    }
}
