package com.java8.DateComparison;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class DateComparison_Java8ChronoUnit {

    public static void main(String[] args) {
        LocalDate dateOfBirth = LocalDate.of(1976, Month.OCTOBER, 15);
        LocalDate currentDate = LocalDate.now();
        long diffInDays = ChronoUnit.DAYS.between(dateOfBirth, currentDate);
        long diffInMonths = ChronoUnit.MONTHS.between(dateOfBirth, currentDate);
        long diffInYears = ChronoUnit.YEARS.between(dateOfBirth, currentDate);

        // These are each totals.. so you'll have to do some calculations to convert it into a specific number of days/months and years.
    }
}
