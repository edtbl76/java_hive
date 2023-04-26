package com.oop.Abstraction;

import java.util.List;

public class SalaryReport implements Report {

    @Override
    public List<Object> run(ReportContext reportContext) {
        System.out.println("Executing salary report");
        return null;
    }
}
