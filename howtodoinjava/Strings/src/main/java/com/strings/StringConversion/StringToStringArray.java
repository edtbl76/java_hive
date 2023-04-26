package com.strings.StringConversion;

import java.util.regex.Pattern;

public class StringToStringArray {

    public static void main(String[] args) {

        // So I've got an ip address, but I want to turn the components into a String array, so I can
        // mess around with them.
        String ipAddy = "192.168.1.1";

        // METHOD 1: Use String.split(), then join them w/ the . delimiter
        String[] octetsStrSplit = ipAddy.split("//.");
        System.out.println(String.join(".", octetsStrSplit));

        // METHOD 2: Do the same thing using Pattern.split()
        /*
            I prefer this because it is quickly extendible for reuse.
         */
        Pattern pattern = Pattern.compile("//.");
        System.out.println(String.join(".", pattern.split(ipAddy)));

        /*
            NOTE: in both cases I'm doing this just to reassemble it and print the result. I get it.. it's
            contrived. Normally, you'd do some processing on the array.

            For instance let's say that given a default gateway, we wanted to populate some IP addresses for a given
            network. We could iterate through a Map of hostnames and host octets, and then replace the element[3] w/
            the other octets.
         */




    }
}
