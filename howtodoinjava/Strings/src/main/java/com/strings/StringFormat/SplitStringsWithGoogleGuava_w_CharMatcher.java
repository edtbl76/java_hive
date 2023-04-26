package com.strings.StringFormat;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import java.util.Scanner;

public class SplitStringsWithGoogleGuava_w_CharMatcher {

    public static void main(String[] args) {
        // Check out the other GoogleGuava matcher first.. that uses Regex for more complicated parsing.
        /*
            NOTE: regex tends to  be complicated and slower to execute, so if you don't need a complicated match
            don't use it.

            CharMatcher is good alternative for simpler use cases.

            - omitEmptyStrings is useful in ensuring we only have data in our results (assuming that "null" isn't a valid value)
            - trimResults() will remove extra white space (THIS IS NOT A DELIMITER) by default
                - it will also trim other characters you specify as a param
         */
        Splitter splitter = Splitter.on(CharMatcher.anyOf(", ")).omitEmptyStrings().trimResults();

        Iterable<String> tokens = splitter.split(getInput());
        for (String t : tokens) {
            System.out.println(t);
        }

    }

    private static String getInput() {
        System.out.println("Enter a string: ");
        return new Scanner(System.in).nextLine();
    }
}
