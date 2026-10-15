import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankAccount {
    private final String accountNumber;
    private String ownerName;
    private double balance;
    private final List<String> history = new ArrayList<>();

    public BankAccount(String accountNumber, String ownerName, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative");
        }
        this.accountNumber = accountNumber;
        setOwnerName(ownerName);
        this.balance = openingBalance;
        history.add(String.format("OPEN %.2f", openingBalance));
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public final void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name required");
        }
        this.ownerName = ownerName.trim();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance += amount;
        history.add(String.format("DEPOSIT %.2f", amount));
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }
        if (amount > balance) {
            history.add(String.format("DECLINED WITHDRAWAL %.2f", amount));
            return false;
        }
        balance -= amount;
        history.add(String.format("WITHDRAW %.2f", amount));
        return true;
    }

    /** @return an unmodifiable copy. */
    public List<String> getHistory() {
        return Collections.unmodifiableList(new ArrayList<>(history));
    }

    @Override
    public String toString() {
        return String.format("%s [%s] balance: %.2f", accountNumber, ownerName, balance);
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC-001", "Tendai", 500.00);
        acc.deposit(250.00);
        acc.withdraw(100.00);
        acc.withdraw(5000.00);
        System.out.println(acc);

        List<String> copy = acc.getHistory();
        copy.forEach(System.out::println);

        try {
            copy.add("FORGED TRANSACTION");
        } catch (UnsupportedOperationException e) {
            System.out.println("History cannot be modified from outside");
        }
        System.out.println("Entries recorded: " + acc.getHistory().size());
    }
}
