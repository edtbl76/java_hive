package StructuralDesignPatterns.Adapter.Create_Adapter_1;

import java.util.ArrayList;
import java.util.List;

public class EmployeeClient {

    public List<Employee> getEmployeeList() {
        List<Employee> employees = new ArrayList<>();

        Employee employeeFromDB = new EmployeeDB(
                "1234", "Superfly", "Snooka", "ss@gmail.com");

        employees.add(employeeFromDB);

        EmployeeLdap employeeFromLdap =
                new EmployeeLdap("chewie", "Solo", "Han", "solo@gmail.com");

        employees.add(new EmployeeAdapterLdap(employeeFromLdap));

        return employees;
    }


}
