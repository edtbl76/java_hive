package com.java8.DateComparison;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class DateComparison_Java8ChronoUnit_Time {

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.of(1976, Month.OCTOBER, 15, 2, 30);
        LocalDateTime dateTime2 = LocalDateTime.now();

        long diffInNano = ChronoUnit.NANOS.between(dateTime, dateTime2);
        long diffInSeconds = ChronoUnit.SECONDS.between(dateTime, dateTime2);
        long diffInMillis = ChronoUnit.MILLIS.between(dateTime, dateTime2);
        long diffInMinutes = ChronoUnit.MINUTES.between(dateTime, dateTime2);
        long diffInHours = ChronoUnit.HOURS.between(dateTime, dateTime2);
    }
}
