package com.emangini.java9.StreamOfDates;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8DateStreams {

    // This is a Java8 workaround for DatesUntilExample
    public static void main(String[] args) {
        System.out.println(getDaysinJava8(LocalDate.now(), 10));
    }

    // Stream of dates w/ 1 day difference
    public static List<LocalDate> getDaysinJava8(LocalDate start, int days) {
        return Stream.iterate(start, date -> date.plusDays(1))
                .limit(days)
                .collect(Collectors.toList());
    }
}
