package exercises.student;

/** Intermediate 2: Student implements Comparable<Student> (natural order = by name). */
public class Student implements Comparable<Student> {
    private final String name;
    private final int marks;

    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() { return name; }
    public int getMarks()   { return marks; }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() { return name + "(" + marks + ")"; }
}
