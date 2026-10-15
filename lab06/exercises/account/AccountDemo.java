package exercises.account;

public class AccountDemo {
    public static void main(String[] args) {
        Account[] accounts = {
            new SavingsAccount("SAV-1", 500),
            new SavingsAccount("SAV-2", 5000),
            new CurrentAccount("CUR-1", 800)
        };

        for (Account a : accounts) {
            a.deposit(100);
            System.out.printf("%s balance %.2f, fee %.2f", a.getNumber(), a.getBalance(), a.monthlyFee());
            a.applyMonthlyFee();
            System.out.printf(" -> after fee %.2f%n", a.getBalance());
        }
    }
}
