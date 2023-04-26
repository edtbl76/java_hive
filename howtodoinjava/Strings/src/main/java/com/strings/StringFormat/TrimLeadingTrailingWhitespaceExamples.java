package com.strings.StringFormat;

public class TrimLeadingTrailingWhitespaceExamples {

    /*
        String.replaceAll(regex, replacement);
            - this replaces ALL matches of 'regex', w/ 'replacement'

        String.replaceFirst(regex, replacement)
            - this replaces the FIRST match of 'regex', w/ 'replacement'

        trim()
        - Shortcut.. this just takes all leading/trailing spaces.

     */

    public static void main(String[] args) {
        String leading  = "   trim_me";
        String trailing = "trim_me    ";
        String both     = "    trim_me    ";

        String regexLead    = "^\\s+";
        String regexLast    = "\\s++$";

        System.out.println("Leading  w/ NoRegex  : [" + leading + "]");
        System.out.println("Trailing w/ NoRegex  : [" + trailing + "]");
        System.out.println("Both     w/ NoRegex  : [" + both + "]");
        System.out.println();
        System.out.println("Leading  w/ RegexLead: [" + leading.replaceAll(regexLead, "") + "]");
        System.out.println("Trailing w/ RegexLead: [" + trailing.replaceAll(regexLead, "") + "]");
        System.out.println("Both     w/ RegexLead: [" + both.replaceAll(regexLead, "") + "]");
        System.out.println();
        System.out.println("Leading  w/ RegexLast: [" + leading.replaceFirst(regexLast, "") + "]");
        System.out.println("Trailing w/ RegexLast: [" + trailing.replaceFirst(regexLast, "") + "]");
        System.out.println("Both     w/ RegexLast: [" + both.replaceFirst(regexLast, "") + "]");
        System.out.println();
        System.out.println("Leading  w/ Trim     : [" + leading.trim() + "]");
        System.out.println("Trailing w/ Trim     : [" + trailing.trim() + "]");
        System.out.println("Both     w/ Trim     : [" + both.trim() + "]");
        System.out.println();


    }
}
