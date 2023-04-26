package com.strings.StringConversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateToString {

    public static void main(String[] args) {

        // Example 1 - basic date to string
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String date = sdf.format(new Date());
        System.out.println(date);           // prints out today's date by default.

        // Example 2 - string to date
        SimpleDateFormat sdfTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String dateString = "11/19/2000 07:25:11";
        try {
            Date dateObject = sdfTime.parse(dateString);
            System.out.println(dateObject);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

    }
}
