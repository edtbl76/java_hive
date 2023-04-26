package com.java8.DateTimeUpdates;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DayOfWeekExamples {

    public static void main(String[] args) {
        // Print "word" given the numeric day of week
        System.out.println(DayOfWeek.of(2));

        // Print the numeric day of week given the ENUM
        DayOfWeek day = DayOfWeek.FRIDAY;
        System.out.println(day.getValue());

        // Get the current date, then  find MONDAY in the current week (Temporal Adjustment)
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.with(DayOfWeek.MONDAY));
    }
}
