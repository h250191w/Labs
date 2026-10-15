public class Employee {
    private static int nextId = 1000;

    private final int employeeId;
    private final String name;
    private final String department;
    private final double salary;

    public Employee(String name, String department, double salary) {
        this.employeeId = nextId++;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public Employee(String name, String department) {
        this(name, department, 500.0);
    }


    public Employee(String name) {
        this(name, "Unassigned");
    }

    public Employee(Employee other) {
        this(other.name, other.department, other.salary);
    }

    @Override
    public String toString() {
        return String.format("#%d %s, %s, %.2f", employeeId, name, department, salary);
    }

    public static void main(String[] args) {
        Employee a = new Employee("Denzel", "Finance", 2200);
        Employee b = new Employee("Elton", "IT");
        Employee c = new Employee("Edric");
        Employee d = new Employee(a);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d + "   <- copy of the first employee, new id");
    }
}
