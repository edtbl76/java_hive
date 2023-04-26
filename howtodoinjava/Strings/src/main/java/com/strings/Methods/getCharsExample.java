package com.strings.Methods;

import java.util.Arrays;

public class getCharsExample {

    /*
        void getChars(int srcBegin, int srcEnd, char[] dest, int destBegin)
            - copies chars of SRC array to DEST array
            - only specified range is copied, and insertion begins at destBegin index.
     */
    public static void main(String[] args) {

        String ipTemplate = "192.168.{subnet}.10";
        String subnetString = "subnet10-clusterId";
        char[] network = new char[2];

        /*

            Remember, it returns void, so you have to make a call() and it will make the change in place.

            The idea is we have some template that needs variable substitution.
            This is some code that pulls the proper value from some determinable location, pulls the values into
            the array, and then subs them in as a string replace() of the '{subnet}' token.

            This gives us another opportunity to use String.valueOf() which can convert the char array back to a String.
            (We could just as easily pull in a string if we wanted.)
         */
        subnetString.getChars(6, 8, network, 0);
        System.out.println("IP address: " + ipTemplate.replace("{subnet}", String.valueOf(network)));

    }

}
