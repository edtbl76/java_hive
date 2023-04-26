package com.java8.StringToDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToDateCustomFormat {

    public static void main(String[] args) {

        String anotherDate = "21 Oct 2019";

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate random = LocalDate.parse(anotherDate, df);

        System.out.println(anotherDate + " parses as " + random);
    }
}
