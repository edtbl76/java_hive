import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class StudentDaoImpl implements StudentDAO {

    // pretend this list is a db
    private final List<Student> students;

    public StudentDaoImpl() {
        students = new ArrayList<>();
        Student student1 = new Student("Spicoli", 0);
        Student student2 = new Student("Cartman", 1);
        students.add(student1);
        students.add(student2);
    }

    @Override
    public void deleteStudent(Student student){
        students.remove(student.getRollNo());
        System.out.println("Student: Roll No " + student.getRollNo() + ", deleted from database");
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public void getStudent(int rollNo) {
    }

    @Override
    public void updateStudent(Student student) {
        students.get(student.getRollNo()).setName(student.getName());
        System.out.println("Student: Roll No " + student.getRollNo() + ", updated in the database");
    }
}
