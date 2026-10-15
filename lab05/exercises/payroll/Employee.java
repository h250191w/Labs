package exercises.payroll;

/** Intermediate 1: the Lab 4 employee hierarchy, repeated here so this exercise is self-contained. */
public class Employee {
    private final String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getName() { return name; }

    public double calculatePay() { return baseSalary; }
}
