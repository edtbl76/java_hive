package com.strings.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarAndDate {

    public static void main(String[] args) {

        // CALENDAR -> DATE
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        System.out.println(date);
        System.out.println(calendar.getTime()); // easier

        // DATE -> CALENDAR
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateInString = "07/25/2011 11:19:00";
        Date date2 = null; // initialize to null.
        try {
            date2 = sdf.parse(dateInString);
        } catch (ParseException pe) {
            pe.printStackTrace();
            System.exit(2); // bail out so the wrong date never gets processed.
        }
        // We can reuse the previous instance of clandar.
        calendar.setTime(date2);
        System.out.println(calendar.getTime());
    }
}
