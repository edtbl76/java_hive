import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
class Employee {

    private final String name;
    private final String dept;
    private final int salary;
    private final List<Employee> directReports;

    public Employee(String name, String dept, int salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        directReports = new ArrayList<>();
    }

    public void add(Employee e) {
        directReports.add(e);
    }

    public void remove(Employee e) {
        directReports.remove(e);
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public String toString() {
        return ("Employee :[Name : " + name + ", dept : " + dept + ", salary : "  + salary + "]");
    }
}
