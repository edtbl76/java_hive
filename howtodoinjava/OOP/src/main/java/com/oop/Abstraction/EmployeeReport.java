package com.oop.Abstraction;

import java.util.List;

public class EmployeeReport implements Report {

    @Override
    public List<Object> run(ReportContext reportContext) {
        System.out.println("Executing employee report.");
        return null;
    }
}
