package com.strings.StringFormat;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

public class TitleCase_w_Java8Streams {

    public static void main(String[] args) {

        System.out.println(titleCaseConversion(null));
        System.out.println(titleCaseConversion(" "));
        System.out.println(titleCaseConversion("a"));
        System.out.println(titleCaseConversion("the  dark knight rises"));
        System.out.println(titleCaseConversion("snake_case"));
        System.out.println(titleCaseConversion("TITLE CASE CONVERSION"));

    }

    private static String titleCaseConversion(String inputString) {

        // return nuttin if it is blank
        if (StringUtils.isBlank(inputString)) {
            return "";
        }

        // if it is a single character, capitalize it on return
        if (StringUtils.length(inputString) == 1) {
            return inputString.toUpperCase();
        }

        // set up storage to be the length of the incoming string (no need to waste space!)
        StringBuffer temp = new StringBuffer(inputString.length());

        // pass it into a stream of space separated "things" (we create the array by splitting on space), then iterate
        Stream.of(inputString.split(" ")).forEach(chunk -> {
            /*
                if the chunk is greater than 1 char, capitalize the first letter and dump it in our temp storage.
                make everything else lower case, and append to temp storage.
             */
            if(chunk.length() > 1)
                temp.append(chunk.substring(0, 1)
                        .toUpperCase())
                .append(chunk.substring(1)
                .toLowerCase());
            // If it's 1, just convert to uppercase
            else
                temp.append(chunk.toUpperCase());

            // add a space in case there are more chunks in the stream.
            temp.append(" ");
        });
        // trim off any extra whitespace, convert the StrBuff to a string and print that shit out.
        return StringUtils.trim(temp.toString());



    }
}
