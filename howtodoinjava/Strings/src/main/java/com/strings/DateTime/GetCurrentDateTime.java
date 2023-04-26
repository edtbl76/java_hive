package com.strings.DateTime;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class GetCurrentDateTime {

    public static void main(String[] args) {

        // Example 1 - GETTING CURRENT DATE (pre Java 8)
        // (using formatter from second example)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dateNow = new Date();      // by default, this is right now!
        System.out.println(sdf.format(dateNow));

        // Example 2 - Java 8.
        LocalDate today = LocalDate.now();
        System.out.println("Today's Date is: " + today);
        System.out.println("Today's Date is: " + LocalDate.now()); // easier.

        // Example 3 Get Current Time - Java 8
        LocalTime time = LocalTime.now();
        System.out.println("The time is now, precisely: " + time);
        System.out.println("The time is now, precisely: " + LocalTime.now());   // easier.

    }
}
