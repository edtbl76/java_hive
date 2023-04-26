package com.strings.Methods;

public class replaceExample {

    public static void main(String[] args) {

        /*
            String replace(char OldChar, char NewChar)
                - returns update string after changing ALL instances of oldChar w/ newChar

            String replace(String target, String replacement)
                - returns new updated string after changing all occurences of target w/ replacement argument

            String replaceFirst(String regex, String replacement)
                - replaces FIRST occurrence of substring that matches regex, w/ the specified replacement

            String replaceAll(String regex, String replacement)
                - replaces ALL occurrences of substring that matches regex, w/ the specified replacement
         */

        String powerJuice = "coffee";
        String optimisticPhrase = "Try Try Again!";

        System.out.println(powerJuice);

        // replace characters
        String candy = powerJuice.replace('c', 't');
        System.out.println(candy);

        // replace by string
        String dead = powerJuice.replace("fee", "fin");
        System.out.println(dead);

        System.out.println(optimisticPhrase);

        // replace First
        String cycle = optimisticPhrase.replaceFirst("Try", "Fail");
        System.out.println(cycle);

        // replace Again
        String refriedBeans = optimisticPhrase.replaceAll("Try", "Fry");
        System.out.println(refriedBeans);
    }
}
