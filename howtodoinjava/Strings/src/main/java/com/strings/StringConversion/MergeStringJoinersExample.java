package com.strings.StringConversion;

import java.util.Scanner;
import java.util.StringJoiner;

public class MergeStringJoinersExample {

    /*
        Check out StringJoinerExample first.

        This demonstrates two string separate string joiners that are merged.
        - content of ARGUMENT joiner is added to THIS joiner (i.e. the joiner in which the method was called against)
        - both joiners persist their delimiters
        - Prefix and Suffix is used of the FIRST joiner on which the method is called.
     */
    public static void main(String[] args) {

        String regex = "[\\s+\\W+]";
        StringJoiner joinerBrackets = new StringJoiner(" " ,"[", "]" );
        StringJoiner joinerBraces = new StringJoiner(" ", "{", "}") ;

        // Get input and split it up.
        String[] tokens = getInput().split(regex);
        for (String t : tokens) {
            // iterate and dump it into the joiners.
            joinerBraces.add(t);
            joinerBrackets.add(t);
        }

        // Before merge
        System.out.println(joinerBraces.toString());
        System.out.println(joinerBrackets.toString());

        // after merge
        joinerBraces.merge(joinerBrackets);
        System.out.println(joinerBraces.toString());

    }

    private static String getInput() {
        System.out.println("Enter a string: ");
        return new Scanner(System.in).nextLine();
    }
}
