public class TransferObjectPatternDemo {

    public static void main(String[] args) {
        StudentBO studentBusinessObject = new StudentBO();

        //print all students
        for (StudentVO student : studentBusinessObject.getAllStudents()) {
            System.out.println("Student: [ Id: " + student.getIdNo() + ", Name: " + student.getName() + " ]");
        }

        //update student
        StudentVO student = studentBusinessObject.getAllStudents().get(0);
        student.setName("Marco");
        studentBusinessObject.updateStudent(student);

        // get the student
        student = studentBusinessObject.getStudent(0);
        System.out.println("Student: [ Id: " + student.getIdNo() + ", Name: " + student.getName() + " ]");
    }
}
