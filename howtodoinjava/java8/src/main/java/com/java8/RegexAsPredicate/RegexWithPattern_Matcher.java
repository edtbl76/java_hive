package com.java8.RegexAsPredicate;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexWithPattern_Matcher {

    // I personally prefer the predicate version.. It is MUCH more readable.
    public static void main(String[] args) {

        Pattern pattern =  Pattern.compile("^(.+)@example.comm$");

        // Create a list of emails.
        List<String> emails = Arrays.asList(
                "ed@example.com", "bubba@example.com", "babygoat@example.com", "v@whatever.com", "ed@nobody.com"
        );

        for (String email: emails) {
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches())
                System.out.println(email);
        }
    }
}
