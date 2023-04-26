package com.strings.Methods;

public class indexOfExamples {

    public static void main(String[] args) {
        /*
            int indexOf(int ch)
                - returns the index of the FIRST OCCURRENCE of the specified character argument in the string.

            int indexOf(int ch, int fromIndex()
                - same as previous, but it starts searching from the specified index.

            int indexOf(String str)
                - returns the index of  the first occurrence of the specified substring
         */

        String lotsOEss = "Mississippi";

        // First Occurrence (Character)
        System.out.println(lotsOEss.indexOf("s"));

        /*
            Use the overloaded version  to start searching from the index + 1 of the single argument version.
         */
        System.out.println(lotsOEss.indexOf('s', (lotsOEss.indexOf('s') + 1)));

        // First occurence (substring)
        System.out.println(lotsOEss.indexOf("is"));

        /*
            Overloading again to find the next occurence based on adding 1 to the result of the finding the first occurence
         */
        System.out.println(lotsOEss.indexOf("is",  (lotsOEss.indexOf("is") + 1)));
    }
}
