/*
This is an example of a class using Instance Variable(s)
 */
public class InstVarEmployee {

    //instance variable is variable for any class.
    public String name;

    // Visible only to THIS class
    private double salary;

    // constructor sets name var.
    InstVarEmployee(String empName) { name = empName; }

    // setter
    void setSalary(double empSal) { salary = empSal; }

    // prints stuff out.
    void printEmployee() {
        System.out.println("Name  :" + name);
        System.out.println("Salary:" + salary);
    }

    public static void main(String[] args) {
        InstVarEmployee iveOne = new InstVarEmployee("Gizmo");
        iveOne.setSalary(1000);
        iveOne.printEmployee();
    }
}
