package com.strings.StringConversion;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class StringJoinerExample {

    /*
        StringJoiner vs. StringBuilder

        - If you join 2 Strings w/ StringBuilder, then you end up appending each string and delimiter in
        alternate sequence
            - and you have to remove the delimiter to the final string

        StringJoiner uses the delimiter as part of the constructor so you just focus on the strings to add.
        - delimiters are added automatically.

        Recommended method.
     */

    public static void main(String[] args) {

        /*
            StringJoiner(CharSequence delimiter)
            StringJoiner(CharSequence delimiter, CharSequence prefix)
            StringJoiner(CharSequence delimiter, CharSequence prefix, CharSequence suffix)
            (prefix and suffix are optional)
         */

        StringJoiner joiner1 = new StringJoiner(",", "[", "]");
        StringJoiner joiner2 = new StringJoiner(",");

        // Lets assume that you've provided a search of terms and the terms are found by Lucene or something along
        //  those lines. We might want to do something w/ the results, so we can group them and return to user or
        // maybe this is logging logic or something.
        List<String> wordsAboutRunning = Arrays.asList("marathon", "sprint", "fartlek", "intervals");
        for (String s : wordsAboutRunning) {
            joiner1.add(s);
            joiner2.add(s);
        }

        System.out.println("Prefix and Suffix: " + joiner1);    // This includes prefix and suffix
        System.out.println(joiner2);    // this is delimiter only

        // Demonstrate StringJoiner with empty

        StringJoiner joiner3 = new StringJoiner(",", "[", "]");
        System.out.println("Joiner Before Using It: " + joiner3);

        joiner3.setEmptyValue("EMPTY");
        System.out.println("Joiner with Empty Value Set: " + joiner3);

        joiner3.add("Hello");
        joiner3.add(" World!");
        System.out.println("Joiner with Data: " + joiner3);

    }
}
