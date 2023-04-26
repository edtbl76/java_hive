package com.oop.Inheritance;

import com.oop.Abstraction.EmployeeReport;

import java.util.List;

public class IManager extends IEmployee {
    private List<EmployeeReport> directReports;

    public List<EmployeeReport> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<EmployeeReport> directReports) {
        this.directReports = directReports;
    }

    @Override
    public String toString() {
       return "Manager [DirectReports " + directReports + ", details: " + super.toString() + "]";
    }
}
