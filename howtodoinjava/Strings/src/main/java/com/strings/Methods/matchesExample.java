package com.strings.Methods;

public class matchesExample {

    /*
        boolean matches (String regex)
            - validates whether or not the String matches a regex argument
     */
    public static void main(String[] args) {

        String hw = "Hello World!";
        String regex1 = "(.*)Hello(.*)";

        String result = hw.matches(regex1) ? "Matches!" : "No match";
        System.out.println(result);
    }
}
