package com.strings.HTML;

import com.strings.MyStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

public class UnescapedHTMLToString {

    public static void main(String[] args) {


        String unescapedString = "<java>public static void maini(String[] args) {...}</java>";

        // METHOD 1: Using Apache Commons Test (StringEscapeUtils)
        String escapedHTML = StringEscapeUtils.escapeHtml4(unescapedString);
        System.out.println(escapedHTML);

        // METHOD 2: DIY
        String escapedHTML2 = MyStringUtils.encodeHtml(unescapedString);
        System.out.println(escapedHTML2);
    }
}
