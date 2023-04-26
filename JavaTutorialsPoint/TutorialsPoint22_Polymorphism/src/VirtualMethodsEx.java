@SuppressWarnings("unused")
class Employee {
    private final String firstName;
    private final String lastName;
    private String title;
    private final int employeeId;

    Employee(String firstName, String lastName, String title, int employeeId) {
        System.out.println("Constructing an Employee.");
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.employeeId = employeeId;
    }

    public void directDeposit() {
        System.out.println("Direct Deposit has been issued for " + firstName + " " + lastName + ".");
    }

    public String toString() {
        return firstName + " " + lastName + ": " + title + " [" + employeeId + "]";
    }

    String getName() {
        return firstName + " " + lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEmployeeId() {
        return employeeId;
    }

}

@SuppressWarnings("unused")
class Salary extends Employee {
    private double salary;

    public Salary(String firstName, String lastName, String title, int employeeId, double salary) {
        super(firstName, lastName, title, employeeId);
        setSalary(salary);
    }

    public void directDeposit() {
        System.out.println("Annual Salary for " + getName() + ": " + salary + ", this deposit: [$" + weeklyAmount() +"]");

    }

    public double getSalary() {
        return salary;
    }

    private void setSalary(double salary) {
        if (salary >= 0.0) {
            this.salary = salary;
        }
    }

    private double weeklyAmount() {
        return salary/52;
    }
}

public class VirtualMethodsEx {

    public static void main(String[] args) {
        Salary s = new Salary("Russell", "Wilson", "Quarterback", 3, 140000000.00);
        Employee e = new Salary("Frank", "Clark", "Edge", 55, 105000000.00);

        System.out.println("Gimme mah money!");
        s.directDeposit();
        e.directDeposit();

        // even though the references are different at compile time, at runtime, when the class is placed on the
        // heap, its explicit type is stored on the heap. This is essentially redundant. Either way.. as a result.
        // we end up w/ the same result.
    }

}
