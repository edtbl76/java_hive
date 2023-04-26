package StructuralDesignPatterns.Adapter.Second_Adapter_2;

import java.util.ArrayList;
import java.util.List;

public class EmployeeClient {

    public List<Employee> getEmployeeList() {
        List<Employee> employees = new ArrayList<>();

        // Employee DB
        Employee employeeFromDB = new EmployeeDB(
                "1234", "Superfly", "Snooka", "ss@gmail.com");

        employees.add(employeeFromDB);

        // Employee LDAP
        EmployeeLdap employeeFromLdap =
                new EmployeeLdap("chewie", "Solo", "Han", "solo@gmail.com");

        employees.add(new EmployeeAdapterLdap(employeeFromLdap));

        // Employee CSV
        EmployeeCSV employeeFromCSV = new EmployeeCSV("567,Sherlock,Holmes,sherlock@holmes.com");
        employees.add(new EmployeeAdapterCSV(employeeFromCSV));

        return employees;
    }


}
