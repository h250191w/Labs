package exercises.payment;

public class MobileMoneyPayment extends Payment {
    private final String phone;

    public MobileMoneyPayment(double amount, String phone) {
        super(amount);
        this.phone = phone;
    }

    public String getPhone() { return phone; }

    @Override
    public String process() {
        return String.format("Requested %.2f from mobile wallet %s", amount, phone);
    }
}
