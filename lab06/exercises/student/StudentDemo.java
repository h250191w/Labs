package exercises.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentDemo {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>(List.of(
            new Student("Tinashe", 64),
            new Student("Anesu", 88),
            new Student("Memory", 71),
            new Student("Blessing", 55)));

        Collections.sort(list);                                   // uses compareTo -> by name
        System.out.println("Natural order (name): " + list);

        // A Comparator is a SEPARATE, alternative ordering: by marks, highest first
        Comparator<Student> byMarksDesc = (a, b) -> Integer.compare(b.getMarks(), a.getMarks());
        list.sort(byMarksDesc);
        System.out.println("By marks (desc):    " + list);
    }
}
