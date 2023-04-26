public class EmployeeTest {

    public static void main(String[] args) {
        Employee empOne = new Employee("Russell Wilson");
        Employee empTwo = new Employee("Pete Carroll");

        empOne.setAge(30);
        empOne.setDesignation("Quarterback");
        empOne.setSalary(10000000);
        empOne.printEmployee();

        empTwo.setAge(60);
        empTwo.setDesignation("Head Coach");
        empTwo.setSalary(1000000);
        empTwo.printEmployee();
    }



}
