package exercises.payment;

public class CardPayment extends Payment {
    private final String cardNumberLast4;

    public CardPayment(double amount, String cardNumberLast4) {
        super(amount);
        this.cardNumberLast4 = cardNumberLast4;
    }

    public String getCardNumberLast4() { return cardNumberLast4; }

    @Override
    public String process() {
        return String.format("Charged %.2f to card ending %s", amount, cardNumberLast4);
    }
}
