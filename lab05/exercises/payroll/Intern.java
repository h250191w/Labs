package exercises.payroll;

/** NEW subclass added in the exercise: the payroll loop below is NOT changed to support it. */
public class Intern extends Employee {
    private final double stipendCap;

    public Intern(String name, double baseSalary, double stipendCap) {
        super(name, baseSalary);
        this.stipendCap = stipendCap;
    }

    /** Interns are paid their base amount but never more than the stipend cap. */
    @Override public double calculatePay() { return Math.min(baseSalary, stipendCap); }
}
