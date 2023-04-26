package com.java8.RegexAsPredicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexToPredicateExample {

    public static void main(String[] args) {

        // Compile regex as predicate
        Predicate<String> emailFilter = Pattern
                .compile("^(.+)@example.com$")
                .asPredicate();

        // Create a list of emails.
        List<String> emails = Arrays.asList(
                "ed@example.com", "bubba@example.com", "babygoat@example.com", "v@whatever.com", "ed@nobody.com"
        );

        // Apply predicate filter.
        List<String> filteredEmails = emails
                .stream()
                .filter(emailFilter)
                .collect(Collectors.toList());

        // DO something (in our case print'em out)
        filteredEmails.forEach(System.out::println);
    }
}
