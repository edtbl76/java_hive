public class MVCPatternDemo {

    public static void main(String[] args) {

        //get student record based on roll no
        Student model = retrieveStudentFromDatabase();

        // Create a view
        StudentView view = new StudentView();

        StudentController controller = new StudentController(model, view);

        controller.updateView();

        // update model data
        controller.setStudentName("Mike");
        controller.updateView();
    }

    private static Student retrieveStudentFromDatabase() {
        Student student = new Student();
        student.setName("Michael");
        student.setRollNo("42");
        return student;
    }
}
