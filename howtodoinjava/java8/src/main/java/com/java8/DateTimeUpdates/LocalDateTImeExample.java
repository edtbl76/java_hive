package com.java8.DateTimeUpdates;

import java.time.LocalDateTime;

public class LocalDateTImeExample {

    public static void main(String[] args) {

        LocalDateTime ldt = LocalDateTime.now();

        System.out.println(ldt.toString());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getNano());
    }
}
