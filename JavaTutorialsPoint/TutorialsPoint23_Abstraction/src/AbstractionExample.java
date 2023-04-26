abstract class Employee {
    String name;
    String address;
    int number;

    public Employee(String name, String address, int number) {
        System.out.println("Inside Employee.");
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public double computePay() {
        System.out.println("Inside Employee.computePay()");
        return 0.0;
    }

    public void mailCheck(){
        System.out.println("Mailing a check to " + this.name + " " + this.address);
    }

    public String toString(){
        return name + " " + address + " " + number;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }
}

class Salary extends Employee {
    private double salary;

    public Salary(String name, String address, int number, double salary) {
        super(name, address, number);
        setSalary(salary);
    }

    public void mailCheck() {
        System.out.println("Within Salary.mailCheck()");
        System.out.println("Mailing check to " + getName() + " with salary " + salary);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary >= 0.0) {
            this.salary = salary;
        }
    }

    public double computePay() {
        System.out.println("Computing salary pay for " + getName());
        return salary/52;
    }
}

public class AbstractionExample {

    public static void main(String[] args) {
        Salary s = new Salary("Jack B. Nimble", "San Francisco, CA", 0, 100000.00);
        Employee e = new Salary("Lori Loughlin", "San Quentin, CA", 1, .75);
        System.out.println("Call mailCheck using Salary reference----");
        s.mailCheck();
        System.out.println("Call mailCheck using Employee reference----");
        e.mailCheck();

    }
}
