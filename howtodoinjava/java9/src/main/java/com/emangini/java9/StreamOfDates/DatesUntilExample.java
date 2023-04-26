package com.emangini.java9.StreamOfDates;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

public class DatesUntilExample {

    /*
        datesUntil()
            - new Method as of Java 9.
            - gives a stream of dates.

        EX:
            Stream<LocalDate> datesUntil(LocalDate end)
            Stream<LocalDate> datesUntil(LocalDate end, Period step)
     */
    public static void main(String[] args) {
        System.out.println(getDaysInJava9(LocalDate.now(), LocalDate.now().plusDays(10)));
        System.out.println(getDaysInJava9Weeks(LocalDate.now(), LocalDate.now().plusWeeks(7)));

    }

    // Stream of dates w/ 1 day difference
    public static List<LocalDate> getDaysInJava9(LocalDate start, LocalDate end) {
        return start.datesUntil(end).collect(Collectors.toList());
    }

    // Stream of dates w/ 1 week difference
    public static List<LocalDate> getDaysInJava9Weeks(LocalDate start, LocalDate end) {
        return start.datesUntil(end, Period.ofWeeks(1)).collect(Collectors.toList());
    }
}
