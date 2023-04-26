package com.strings.StringConversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate {

    /*

     */
    public static void main(String[] args) {

        /* Setting up the Formatter does two things
            1.) it creates an input constraint around strings we'll accept as an arg to parse()
            2.) it provides instruction on how we want date objects formatted into Strings w/ format()
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        // Now, create a dateString to use. (This would normally be input or read in or something)
        String dateString = "10-Oct-1985";

        // (Do our handy dandy wrap)
        try {
            // parse the input and print it out as a date object
            Date date = simpleDateFormat.parse(dateString);     // String to Date
            System.out.println(date);

            // format the date object back into a string.
            String dateStr = simpleDateFormat.format(date);     // Date To String
            System.out.println(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
