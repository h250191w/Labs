package exercises.payroll;

public class PayrollDemo {

    /** This method was written before Intern existed and works unchanged: open for extension, closed for modification. */
    static double totalPayroll(Employee[] staff) {
        double total = 0;
        for (Employee e : staff) {
            System.out.printf("%-8s (%s) %.2f%n", e.getName(), e.getClass().getSimpleName(), e.calculatePay());
            total += e.calculatePay();
        }
        return total;
    }

    public static void main(String[] args) {
        Employee[] staff = {
            new Employee("Kudzai", 2000),
            new Manager("Nyasha", 3500, 800),
            new Developer("Tatenda", 3000, 10),
            new Intern("Rufaro", 900, 600)
        };
        System.out.printf("Total payroll: %.2f%n", totalPayroll(staff));
    }
}
