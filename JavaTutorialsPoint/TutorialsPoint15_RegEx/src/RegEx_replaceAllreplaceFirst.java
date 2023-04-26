import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx_replaceAllreplaceFirst {

    private static String REGEX = "dog";
    private static String INPUT = "The dog says meow. " + "All dogs say meow.";
    private static String REPLACE = "cat";

    public static void main(String[] args) {

        Pattern p = Pattern.compile(REGEX);

        Matcher m = p.matcher(INPUT);

        // Replace First
        INPUT = m.replaceFirst(REPLACE);
        System.out.println(INPUT);

        // Replace All
        INPUT = m.replaceAll(REPLACE);
        System.out.println(INPUT);

    }
}
