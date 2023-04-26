import java.util.List;

@SuppressWarnings("ALL")
interface StudentDAO {
    List<Student> getAllStudents();
    void getStudent(int rollNo);
    void updateStudent(Student student);
    void deleteStudent(Student student);
}
