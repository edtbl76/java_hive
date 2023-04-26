import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegEx_appendReplacementAppendTail {

    private static String REGEX = "a*b";  // Matches b, and any number of a's (or none at all) preceding it.
    private static String INPUT = "aabfooaabfooabfooboogers";
    private static String REPLACE = "-";

    public static void main(String[] args) {

        Pattern p = Pattern.compile(REGEX);

        Matcher m = p.matcher(INPUT);

        // Create some storage to hold the new String.
        StringBuffer sb = new StringBuffer();

        while(m.find()) {
            m.appendReplacement(sb, REPLACE);  // every time we find a match, we replace it w/ -
        }
        m.appendTail(sb);  // Gathers remainder of original string and puts it in the String Buffer.
        System.out.println(sb.toString());
    }
}
