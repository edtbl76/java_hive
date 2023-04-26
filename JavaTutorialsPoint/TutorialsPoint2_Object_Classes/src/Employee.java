class Employee {

    private final String name;
    private int age;
    private String designation;
    private double salary;

    // This is the constructor of the class Employee
    Employee(String name) {
        this.name = name;
    }

    // setters
    void setAge(int empAge) { age = empAge; }
    void setDesignation(String empDesignation) { designation = empDesignation; }
    void setSalary(double empSalary) { salary = empSalary; }

    // getters
    private int getAge() { return age; }
    private String getDesignation() { return designation; }
    private double getSalary() { return salary; }

    void printEmployee() {
        System.out.println("Name       : " + this.name);
        System.out.println("Age        : " + getAge());
        System.out.println("Designation: " + getDesignation());
        System.out.println("Salary     : " + getSalary());
    }

}
