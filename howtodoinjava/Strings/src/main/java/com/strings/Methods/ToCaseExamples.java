package com.strings.Methods;

import java.util.Locale;

public class ToCaseExamples {

    /*
        THIS IS A COLLECTION OF UPPER/LOSER CASE convenience functions

        String toUpperCase(Locale locale)
            - converts the string to upper case string using rules defined by specified locale

        String toUpperCase();
            - same, but uses default  locale

        String toLowerCase(Locale locale)
            - converts the string to lower case string using rules defined by specified locale

        String toLowerCase()
            - same using default locale.
     */

    public static void main(String[] args) {

        // Defaults
        String mouse = "too quiet.";
        String lion = "TOO LOUD";
        System.out.println("Mouse: " + mouse);
        System.out.println("Lion: " + lion);

        // Quiet to loud, loud to quiet. default locale
        System.out.println("Mouse: " + mouse.toUpperCase());
        System.out.println("Lion: " + lion.toLowerCase());

        // Do it again with a different locale
        System.out.println("Mandarin Mouse: " + mouse.toUpperCase(Locale.CHINESE));
        System.out.println("Korean King of the Jungle: " + lion.toLowerCase(Locale.KOREAN));

    }
}
