package com.strings.StringFormat;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TitleCase_w_Java8Streams2 {

    public static void main(String[] args) {

        List<String> convertMe = Arrays.asList(null, " ", "a", "man of steel", "suicide_squad", "JUSTICE LEAGUE");
        convertMe.forEach(thing -> System.out.println(titleCaseConversion(thing)));

    }

    private static String titleCaseConversion(String inputString) {

        // blank return nil
        if (StringUtils.isBlank(inputString)) {
            return "";
        }

        // if 1, capitalize and go
        if (StringUtils.length(inputString) == 1) {
            return inputString.toUpperCase();
        }

        // Create Temp store based on size of input string
        StringBuffer tempStore = new StringBuffer(inputString.length());

        /*
            Create a stream of space separated chunks, then iterate over it.
         */
        Stream.of(inputString.split(" ")).forEach(chunk ->  {
            char[] chars = chunk.toLowerCase().toCharArray();           // convert entire string to lower case, then to a char array
            chars[0] = Character.toUpperCase(chars[0]);                 // capitalize first character via Character.toUpperCase() and set
                                                                        // the value to that first character. (odd syntax)
            // dump the char array into a new heap-allocated string, tack on an empty space
            // in case there are more chunks and stuff this in the temp buffer.
            tempStore.append(new String(chars)).append(" ");

        });
        return StringUtils.trim(tempStore.toString());      // pump SB to String, trim off the spaces and  return to sender.
    }
}
