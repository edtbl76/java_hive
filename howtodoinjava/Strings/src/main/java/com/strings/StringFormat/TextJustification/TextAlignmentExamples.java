package com.strings.StringFormat.TextJustification;

import org.apache.commons.lang3.StringUtils;

public class TextAlignmentExamples {

    public static String sampleText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt "
            + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
            + "nisi ut aliquip ex ea commodo consequat.";

    // NOTE: Look at the last line of the output to get a feel for the justification.
    public static void main(String[] args) {

        System.out.println("LEFT Justify: ");
        StringJustifyTool util = new StringJustifyTool(50, StringJustifyTool.Justification.LEFT);
        System.out.println(util.format(sampleText));

        System.out.println("\nRIGHT JUSTIFY: ");
        util = new StringJustifyTool(50, StringJustifyTool.Justification.RIGHT);
        System.out.println(util.format(sampleText));

        System.out.println("\nCENTERED: ");
        util = new StringJustifyTool(50, StringJustifyTool.Justification.CENTER);
        System.out.println(util.format(sampleText));


    }
}
