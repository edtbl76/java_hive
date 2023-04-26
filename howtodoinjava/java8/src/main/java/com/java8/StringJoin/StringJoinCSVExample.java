package com.java8.StringJoin;

import java.time.ZoneId;

public class StringJoinCSVExample {

    /*
        Java 8 introduces the ability to concatenate CSV w/ join()

        - prior to that the method was
            1.) use String.split() to split a string based on a parameterized token
            2.) return a list of string "tokens" as an array
            3.) iterate through list/array of String then use StringBuilder/StringBuffer object to concatenate those
            tokens to get the final CSV
     */
    public static void main(String[] args) {
        String joined = String.join("/", "/usr","local","bin");
        System.out.println(joined);

        // FYI.. this is a way to list all of the available TimeZone Ids.
        String ids = String.join(", ", ZoneId.getAvailableZoneIds());
        System.out.println(ids);
    }
}
