package com.java8.DateTimeUpdates;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class OffsetExamples {

    public static void main(String[] args) {

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println(offsetDateTime.toString());

        offsetDateTime = OffsetDateTime.now(ZoneId.of("+05:30"));
        System.out.println(offsetDateTime.toString());

        offsetDateTime = OffsetDateTime.now(ZoneId.of("-06:30"));
        System.out.println(offsetDateTime.toString());

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/Paris"));

    }
}
