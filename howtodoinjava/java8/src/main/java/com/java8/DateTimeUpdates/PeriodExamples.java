package com.java8.DateTimeUpdates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class PeriodExamples {

    public static void main(String[] args) {
        Period period = Period.ofDays(6);
        System.out.println(period.toString());

        period = Period.ofMonths(6);
        System.out.println(period.toString());

        period = Period.between(LocalDate.now(), LocalDate.now().plusDays(60));
        System.out.println(period.toString());
    }
}
