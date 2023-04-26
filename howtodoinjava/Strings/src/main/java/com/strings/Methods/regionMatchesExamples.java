package com.strings.Methods;

public class regionMatchesExamples {

    /*
        boolean regionMatches(int srcOffset, String dest, int destOffset, int len)
            - compares substring of input to substring of specified string

        boolean regionMatches(boolean ignoreCase, int srcoffset, String dest, int destoffset, int len)
            - Another variation to add in case sensitivity
     */
    public static void main(String[] args) {


        String result =
                ("MacDonald".regionMatches(2, "mcdonald", 1, 2 )) ? "Match" : "No Match";
        String resultNoCase =
                ("MacDonald".regionMatches(true, 2, "mcdonald", 1, 2 )) ? "Match" : "No Match";
        System.out.println("Case Specific: " + result + "\nCase NonSpecific: " + resultNoCase);

    }
}
