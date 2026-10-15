/**
 Lab 4 - Exercises 1 and 2: Student and Teacher both reuse Person.display().
 */
public class PersonDemo {
    public static void main(String[] args) {
        Person person = new Person("Alice", 40);
        Student student = new Student("Brian", 20, "Computer Science");
        Teacher teacher = new Teacher("Mrs Moyo", 45, "Mathematics");

        person.display();
        student.display();     // inherited from Person
        teacher.display();     // inherited from Person

        System.out.println("Student course: " + student.getCourse());
        System.out.println("Teacher subject: " + teacher.getSubject());
        System.out.println("Student is a Person? " + (student instanceof Person));
        System.out.println("Teacher is a Person? " + (teacher instanceof Person));
    }
}
