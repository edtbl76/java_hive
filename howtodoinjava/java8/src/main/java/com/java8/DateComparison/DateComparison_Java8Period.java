package com.java8.DateComparison;

import java.time.LocalDate;
import java.time.Period;

public class DateComparison_Java8Period {

    public static void main(String[] args) {
        LocalDate endOfCentury = LocalDate.of(2099, 12, 31);
        LocalDate now = LocalDate.now();

        Period diff = Period.between(now, endOfCentury);
        System.out.format("Difference is %d years %d months, and %d days",
                diff.getYears(), diff.getMonths(), diff.getDays());
    }
}
