package com.strings.StringFormat;

import org.apache.commons.text.WordUtils;

public class TitleCase_w_WordUtils {

    public static void main(String[] args) {

        // METHOD1 - Apache commons WordUtils
        final char[] delimiters = { ' ', '_' };         // The delimiter tells me where caps should begin.

        System.out.println(WordUtils.capitalizeFully(null, delimiters));
        System.out.println(WordUtils.capitalizeFully(" ", delimiters));
        System.out.println(WordUtils.capitalizeFully("a", delimiters));
        System.out.println(WordUtils.capitalizeFully("batman begins", delimiters));
        System.out.println(WordUtils.capitalizeFully("string_operation", delimiters));
        System.out.println(WordUtils.capitalizeFully("TITLE CASE CONVERSION", delimiters));


    }
}
