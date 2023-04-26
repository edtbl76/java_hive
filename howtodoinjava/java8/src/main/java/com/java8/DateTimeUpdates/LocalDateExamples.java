package com.java8.DateTimeUpdates;

import java.time.LocalDate;

public class LocalDateExamples {
    public static void main(String[] args) {
        LocalDate localDate  = LocalDate.now();

        System.out.println(localDate.toString());
        System.out.println(localDate.toEpochDay());
        System.out.println(localDate.getDayOfWeek().toString());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.isLeapYear());
        System.out.println(localDate.plusDays(12).toString());
    }
}
