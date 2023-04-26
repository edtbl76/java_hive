package StructuralDesignPatterns.Adapter.Second_Adapter_2;

import java.util.List;

public class AdapterDemo {

    public static void main(String[] args) {
        EmployeeClient client = new EmployeeClient();
        List<Employee> employees = client.getEmployeeList();
        System.out.println(employees);
    }

}
