package com.strings.Methods;

import java.util.Arrays;

public class splitExample {

    /*
        String[] split(string regex, int limit)
            - splits string and returns array of sub-strings that match given regex
            - 'limit' is the maximum number of elements in the array

        String[] split(string regex)
            - same as before, but no limit to # of elements in the resultant array
     */
    public static void main(String[] args) {

        /*
        EXAMPLE 1: split w/o a limiter.
         */
        String classC = "192.168.1.1";

        System.out.println("IP Address: " + classC);

        // You need the double backslash to escape the period in regex.
        String[] octets = classC.split("\\.");

        // Print the Array for posterity
        System.out.println("Separate Octets: " + Arrays.toString(octets));

        //  This iterates through the results and gives me the binary representation of each octet of the Network
        // ID only
        for (String o : octets) {
            System.out.println("Binary Octet: " + Integer.toBinaryString(Integer.parseInt(o)));
        }



        /*
        EXAMPLE 2 w/ a limiter.
        - this shows a basic example of URL conversion for something like API Gateway management.
         */
        String externalApiString = "/api/v1/endpoints/moreendpoints/gettingdumb/okquitit";
        System.out.println("http:/myservice" + externalApiString );

        String[] segments = externalApiString.split("/", 4);

        System.out.println("Segments: " + Arrays.toString(segments));

        System.out.println("Internal Call: https://loadbalancer.region.com:8080/" + segments[segments.length -1]);

    }
}
