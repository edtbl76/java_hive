package com.java8.DateComparison;

import org.joda.time.*;
import org.joda.time.chrono.GregorianChronology;

public class JodaTimeComparison_old {

    /*
        JodaTime was the library used prior to Java 8 (and it was actually the inspiration
        for the Java 8 Date/Time Libraries.
     */
    public static void main(String[] args) {
        DateTime dateOfBirth = new DateTime(1976, 10, 15, 2, 30, GregorianChronology.getInstance());
        DateTime currentDate = new DateTime();
        Days diffInDays = Days.daysBetween(dateOfBirth, currentDate);
        Hours diffInHours = Hours.hoursBetween(dateOfBirth, currentDate);
        Minutes diffInMinutes = Minutes.minutesBetween(dateOfBirth, currentDate);
        Seconds diffInSeconds = Seconds.secondsBetween(dateOfBirth, currentDate);

        System.out.println("Difference In Days   : " + diffInDays);
        System.out.println("Difference In Hours  : " + diffInHours);
        System.out.println("Difference In Minutes: " + diffInMinutes);
        System.out.println("Difference In Seconds: " + diffInSeconds);

    }
}
