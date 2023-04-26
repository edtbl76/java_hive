import java.util.Comparator;
import java.util.List;

class Student {

    private final int id;
    private final String name;
    private final String grade;

    public Student(int id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}

class SortbyId implements Comparator<Student> {

    @Override
    public int compare(Student student1, Student student2) {
        return student1.getId() - student2.getId();
    }
}

class SortbyName implements Comparator<Student> {

    @Override
    public int compare(Student student1, Student student2) {
        return student1.getName().compareTo(student2.getName());
    }
}

public class ComparatorSort {

    public static void main(String[] args) {

        List<Student> Students = new java.util.ArrayList<>(List.of(
                new Student(1, "Ed", "4th"),
                new Student(2, "Vanessa", "1st"),
                new Student(3, "Gravy", "7th")
        ));

        /*
            Print unsorted.
            - foreach is slow, but this is ok.
         */
        System.out.println("Unsorted: ");
        Students.forEach(System.out::println);

        /*
            Sort by id, then print.
            - foreach is slow, but this is ok.
         */
        Students.sort(new SortbyId());
        System.out.println("\nSorted by Id: ");
        Students.forEach(System.out::println);

        /*
            Sort by name, then print.
            - foreach is slow, but this is ok.
         */
        Students.sort(new SortbyName());
        System.out.println("\nSorted by Name: ");
        Students.forEach(System.out::println);


    }
}
