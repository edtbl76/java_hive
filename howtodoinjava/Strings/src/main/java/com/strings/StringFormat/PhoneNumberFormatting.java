package com.strings.StringFormat;

public class PhoneNumberFormatting {

    private static final String REGEX = "(\\d{3})(\\d{3})(\\d+)";
    private static final String FORMAT_REPLACEMENT = "($1) $2-$3";

    public static void main(String[] args) {

        String tenDigits = "1234567890";
        /*
            technically we could make some changes to the FORMAT_REPLACEMENT for different formatting options
            (i.e. templatizations)

            We should also make sure that the input is valid
                - ten characters long.
                - all of the chars are numbers
                - potentially ensure that the area codes are valid, etc.
         */
        String number = tenDigits.replaceFirst(REGEX, FORMAT_REPLACEMENT);
        System.out.println(number);
    }
}
