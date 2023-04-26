package com.strings.StringFormat.TextJustification;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class StringJustifyTool {

    private static final long versionID = 1L;

    public enum Justification {
        LEFT,
        CENTER,
        RIGHT
    }

    /** Current justification for cormatting **/
    private Justification currentJustification;

    /** Current max length in a line **/
    private int maxChars;

    public StringJustifyTool(int maxChars, Justification justification) {

        switch (justification) {
            case LEFT:
            case CENTER:
            case RIGHT:
                this.currentJustification = justification;
                break;
            default:
                throw new IllegalArgumentException("invalid justification argument");
        }

        if (maxChars < 0) {
            throw new IllegalArgumentException("maxChars cannot be < 0");
        }
        this.maxChars = maxChars;
    }

    public StringBuffer format(Object input, StringBuffer where, FieldPosition ignore) {
        String s = input.toString();
        List<String> strings = splitInputString(s);
        ListIterator<String> listIterator = strings.listIterator();

        while (listIterator.hasNext()) {

            String wanted = listIterator.next();

            // position spaces correctly
            switch (currentJustification) {
                case RIGHT:
                    pad(where, maxChars - wanted.length());
                    where.append(wanted);
                    break;
                case CENTER:
                    int toAdd = maxChars - wanted.length();
                    pad(where, toAdd / 2);
                    where.append(wanted);
                    pad(where, toAdd - toAdd/2);
                    break;
                case LEFT:
                    where.append(wanted);
                    pad(where, maxChars - wanted.length());
                    break;
            }
            where.append("\n");
        }
        return where;
    }

    protected final void pad(StringBuffer to, int howMany) {
        for (int i = 0;i < howMany; i++)
            to.append(' ');
    }

    String format(String s) {
        return format(s, new StringBuffer(), null).toString();
    }

    // ParseObject is required to implement, but not very useful.
    public Object parseObject(String src, ParsePosition pos) {
        return src;
    }

    private List<String> splitInputString(String str) {
        List<String> list = new ArrayList<>();
        if (str == null)
            return list;
        for  (int i = 0; i < str.length(); i = i + maxChars) {
            int endIdx = Math.min(i + maxChars, str.length());
            list.add(str.substring(i, endIdx));
        }
        return list;

    }

}
