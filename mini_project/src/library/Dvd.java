package library;

import java.util.List;

public class Dvd extends LibraryItem {

    private static final int LOAN_PERIOD_DAYS = 7;

    private final int durationMinutes;

    public Dvd(String id, String title, int durationMinutes) {
        super(id, title);
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("DVD duration must be positive");
        }
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public int loanPeriodDays() {
        return LOAN_PERIOD_DAYS;
    }

    @Override
    public String typeName() {
        return "DVD";
    }

    @Override
    public List<String> extraFields() {
        return List.of(String.valueOf(durationMinutes));
    }

    @Override
    public String toString() {
        return super.toString() + ", " + durationMinutes + " min";
    }
}
