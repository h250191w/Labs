import java.time.LocalDate;

/**
 * Lab 3 - Exercise 4: an immutable class.
 *
 * Design choices:
 *  - final class: a subclass could add mutable state or override getters and break immutability.
 *  - private final fields: assigned once in the constructor and never reassigned.
 *  - no setters: nothing can change the state after construction.
 *  - validation in the constructor: an invalid object can never exist.
 *  - LocalDate is itself immutable, so exposing it through a getter is safe. A mutable field
 *    (such as java.util.Date or a List) would need a defensive copy on the way in and out.
 *  Benefits: thread-safe without locks, safe to share, safe as a HashMap key.
 */
public final class ImmutablePerson {
    private final String name;
    private final LocalDate dateOfBirth;

    public ImmutablePerson(String name, LocalDate dateOfBirth) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth must be in the past");
        }
        this.name = name.trim();
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /** "Changing" a property returns a new object and leaves this one untouched. */
    public ImmutablePerson withName(String newName) {
        return new ImmutablePerson(newName, dateOfBirth);
    }

    @Override
    public String toString() {
        return name + " (born " + dateOfBirth + ")";
    }

    public static void main(String[] args) {
        ImmutablePerson original = new ImmutablePerson("Farai", LocalDate.of(2001, 5, 14));
        ImmutablePerson renamed = original.withName("Farai M.");

        System.out.println(original);
        System.out.println(renamed);
    }
}
