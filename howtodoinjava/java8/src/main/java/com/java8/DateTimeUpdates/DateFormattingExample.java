package com.java8.DateTimeUpdates;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateFormattingExample {

    public static void main(String[] args) {
        DateTimeFormatterBuilder formatterBuilder = new DateTimeFormatterBuilder();
        formatterBuilder.append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                .appendLiteral("-")
                .appendZoneOrOffsetId();
        DateTimeFormatter formatter = formatterBuilder.toFormatter();
        System.out.println(formatter.format(ZonedDateTime.now()));
    }

}
