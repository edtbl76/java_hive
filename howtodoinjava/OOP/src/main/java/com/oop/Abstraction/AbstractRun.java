package com.oop.Abstraction;

public class AbstractRun {
    public static void main(String[] args) {
        ReportContext reportContext = new ReportContext();

        Report eReport = new EmployeeReport();
        eReport.run(reportContext);

        Report sReport = new SalaryReport();
        sReport.run(reportContext);
    }
}
