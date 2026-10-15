public class Student {
    private String name;
    private int marks;

    public Student(String name, int marks) {
        setName(name);
        setMarks(marks);
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        this.name = name.trim();
    }

    public int getMarks() {
        return marks;
    }

    /** Rejects marks outside the range 0 to 100. */
    public final void setMarks(int marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100, got " + marks);
        }
        this.marks = marks;
    }

    public String getGrade() {
        if (marks >= 70) return "A";
        if (marks >= 60) return "B";
        if (marks >= 50) return "C";
        return "F";
    }

    @Override
    public String toString() {
        return String.format("%s: %d (%s)", name, marks, getGrade());
    }

    public static void main(String[] args) {
        Student s = new Student("Chipo", 68);
        System.out.println(s);

        s.setMarks(91);
        System.out.println(s);

        try {
            s.setMarks(120);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println("Marks unchanged after rejection: " + s.getMarks());
    }
}
