public class LevelAdvice {

    enum Level { LOW, MEDIUM, HIGH }

    static String adviceFor(Level level) {
        // A switch expression over an enum needs no default: the compiler checks every constant is covered, so adding a constant later produces a compile error here.
        return switch (level) {
            case LOW -> "Low risk: carry on as normal.";
            case MEDIUM -> "Medium risk: monitor the situation closely.";
            case HIGH -> "High risk: take action immediately.";
        };
    }

    public static void main(String[] args) {
        for (Level level : Level.values()) {
            System.out.println(level + " (ordinal " + level.ordinal() + "): " + adviceFor(level));
        }
        System.out.println("Parsed from text: " + Level.valueOf("MEDIUM"));
    }
}
