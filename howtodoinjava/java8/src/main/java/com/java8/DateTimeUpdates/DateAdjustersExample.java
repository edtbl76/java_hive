package com.java8.DateTimeUpdates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class DateAdjustersExample {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(1976, Month.OCTOBER, 15);
        System.out.println(date);

        LocalDate endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(endOfMonth.toString());

        LocalDate nextTuesday = date.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        System.out.println(nextTuesday.toString());
    }
}
