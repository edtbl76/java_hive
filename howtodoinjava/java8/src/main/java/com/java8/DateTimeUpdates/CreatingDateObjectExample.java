package com.java8.DateTimeUpdates;

import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.ZoneOffset;

public class CreatingDateObjectExample {

    public static void main(String[] args) {
        // Builder Pattern
        OffsetDateTime date1 = Year.of(2019)
                .atMonth(Month.OCTOBER)
                .atDay(21)
                .atTime(17, 36)
                .atOffset(ZoneOffset.of("-04:00"));

        System.out.println(date1);

        // Factory Method
        OffsetDateTime date2 = OffsetDateTime
                .of(2019, 10, 21, 17, 39, 0, 0,
                        ZoneOffset.of("-04:00"));
        System.out.println(date2);
    }
}
