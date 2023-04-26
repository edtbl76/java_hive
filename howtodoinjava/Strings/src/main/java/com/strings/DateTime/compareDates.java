package com.strings.DateTime;

import java.time.LocalDate;
import java.util.Date;

public class compareDates {

    public static void main(String[] args) {

        // Using compareTo()
        Date date1 = new Date();
        Date date2 = new Date();

        int compare = date1.compareTo(date2);

        if (compare > 0) {
            System.out.println("The first date is later... which is all sorts of weird.");
        } else if (compare < 0) {
            System.out.println("The Second date is later... which makes more sense");
        } else {
            System.out.println("This make the absolute most sense... the dates are equal.");
        }

        // use LocalDate's override of equals()
        LocalDate today = LocalDate.now();
        LocalDate bday = LocalDate.of(1976, 10, 15);

        if(bday.equals(today)) {
            System.out.println("Happy Birthday!");
        } else {
            System.out.printf("Today %s and the provided date %s aren't the same %n", today, bday);
        }
    }
}
