import java.util.List;

/**
  Lab 10 - Exercise 4: streams over a list of records. Print the names of students above the class average, sorted alphabetically.
 */
public class AboveAverageStudents {

    record Student(String name, int marks) { }

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Tendai", 72),
                new Student("Rudo", 55),
                new Student("Farai", 88),
                new Student("Chipo", 91),
                new Student("Blessing", 43),
                new Student("Anesu", 67));

        double average = students.stream()
                .mapToInt(Student::marks)
                .average()
                .orElse(0);
        System.out.printf("Class average: %.2f%n", average);

        System.out.println("Above average:");
        students.stream()
                .filter(s -> s.marks() > average)
                .map(Student::name)
                .sorted()
                .forEach(name -> System.out.println("  " + name));
    }
}
