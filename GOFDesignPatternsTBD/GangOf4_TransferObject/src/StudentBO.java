import java.util.ArrayList;
import java.util.List;

class StudentBO {

    // list is working as a db
    private final List<StudentVO> students;

    public StudentBO() {
        students = new ArrayList<>();
        StudentVO stud1 = new StudentVO("Roberto", 0);
        StudentVO stud2 = new StudentVO("Vladimir", 1);
        students.add(stud1);
        students.add(stud2);
    }

    @SuppressWarnings("unused")
    public void deleteStudent(StudentVO student) {
        students.remove(student.getIdNo());
        System.out.println("Student: Id " + student.getIdNo() + ", deleted from database.");
    }

    // get list of students from DB
    public List<StudentVO> getAllStudents() {
        return students;
    }

    public StudentVO getStudent(int idNo) {
        return students.get(idNo);
    }

    public void updateStudent(StudentVO student){
        students.get(student.getIdNo()).setName(student.getName());
        System.out.println("Student: Id " + student.getIdNo() + ", updated in the database.");
    }
}
