package com.strings.DateTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GetDateParts {

    public static void main(String[] args) {

        // Get Parts of the Date using GregorianCalendar()  <--- PRE Java 8
        Calendar calendar = new GregorianCalendar();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);

        int hour = calendar.get(Calendar.HOUR); //12
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY); //24
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millis = calendar.get(Calendar.MILLISECOND);

        // Java8 (for some things)
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        int year1 = today.getYear();
        int month1 = today.getMonthValue();
        int dayOfMonth1 = today.getDayOfMonth();
        int dayOfWeek1 = today.getDayOfWeek().getValue();

        int hour1 = now.getHour();
        int minute1 = now.getMinute();
        int second1 = now.getSecond();
        int nano = now.getNano();



    }
}
