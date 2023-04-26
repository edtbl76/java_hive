package com.java8.StringToDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StringToISO8601Format {

    public static void main(String[] args) {
        String date = "2009-12-31";

        LocalDate ldate = LocalDate.parse(date);
        System.out.println("Date: " + date);

        String dateTime = "2009-12-21T23:59";

        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        System.out.println("Date/Time: " + localDateTime);
    }
}
