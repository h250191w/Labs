package exercises.payment;

/**
 * Challenge: two designs for the same job.
 *
 * DESIGN 1 - instanceof pattern matching (payV1): the PROCESSOR decides what to do for each type.
 * DESIGN 2 - polymorphic process() (payV2):      each PAYMENT decides what to do for itself.
 */
public class PaymentProcessor {

    // ---------- Design 1 ----------
    public String payV1(Payment p) {
        if (p instanceof CardPayment c) {
            return String.format("Charged %.2f to card ending %s", c.getAmount(), c.getCardNumberLast4());
        } else if (p instanceof MobileMoneyPayment m) {
            return String.format("Requested %.2f from mobile wallet %s", m.getAmount(), m.getPhone());
        }
        // CashPayment (added later) falls through here until someone remembers to edit this method!
        throw new IllegalArgumentException("Unsupported payment type: " + p.getClass().getSimpleName());
    }

    // ---------- Design 2 ----------
    public String payV2(Payment p) {
        return p.process();      // no type checks at all
    }
}
