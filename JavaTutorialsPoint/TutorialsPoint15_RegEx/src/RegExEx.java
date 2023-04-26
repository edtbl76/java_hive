import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExEx {

    public static void main(String[] args) {

        // String to be scanned to find the pattern.
        String line = "This order was placed for QT3000! OK?";

        //The pattern
        String pattern = "(.*)(\\d+)(.*)";
        Pattern p = Pattern.compile(pattern);

        // MatchIt!
        Matcher m = p.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
        } else {
            System.out.println("No Mas!");
        }
        System.out.println("Capturing Groups: " + m.groupCount());

    }
}
