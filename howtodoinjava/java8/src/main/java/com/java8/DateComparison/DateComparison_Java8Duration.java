package com.java8.DateComparison;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

public class DateComparison_Java8Duration {

    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.of(1976, Month.OCTOBER, 15, 2, 30);
        LocalDateTime now = LocalDateTime.now();

        int diffInNano = Duration.between(dt, now).getNano();
        long diffInSeconds = Duration.between(dt, now).getSeconds();
        long diffInMillis = Duration.between(dt, now).toMillis();
        long diffInMinutes = Duration.between(dt, now).toMinutes();
        long diffInHours = Duration.between(dt, now).toHours();
    }
}
