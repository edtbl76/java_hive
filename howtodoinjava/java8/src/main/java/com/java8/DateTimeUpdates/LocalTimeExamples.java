package com.java8.DateTimeUpdates;

import java.time.LocalTime;

public class LocalTimeExamples {

    public static void main(String[] args) {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.toString());
        System.out.println(localTime.getHour());
        System.out.println(localTime.getMinute());
        System.out.println(localTime.getSecond());
        System.out.println(localTime.MIDNIGHT);
        System.out.println(localTime.NOON);

        localTime  = LocalTime.of(5, 15);
        System.out.println(localTime.toString());
    }
}
