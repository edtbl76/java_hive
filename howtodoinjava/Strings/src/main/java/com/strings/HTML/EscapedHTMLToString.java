package com.strings.HTML;

import org.apache.commons.text.StringEscapeUtils;

public class EscapedHTMLToString {

    public static void main(String[] args) {

        String escapedString = "&lt;java&gt;public static void main(String[] args) {...}&lt;/java&gt;";

        String removeEscapes = StringEscapeUtils.unescapeHtml4(escapedString);
        System.out.println(removeEscapes);

    }
}
