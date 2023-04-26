public class DaoPatternDemo {
    public static void main(String[] args) {
        StudentDAO studentDao = new StudentDaoImpl();

        //print all
        for(Student student : studentDao.getAllStudents()) {
            System.out.println("Student: [ RollNo: " + student.getRollNo() + ", Name: " + student.getName() + " ]");
        }

        //update
        Student student = studentDao.getAllStudents().get(0);
        student.setName("Mike");
        studentDao.updateStudent(student);

        //get him
        studentDao.getStudent(0);
        System.out.println("Student: [ RollNo: " + student.getRollNo() + ", Name: " + student.getName() + " ]");

    }
}
