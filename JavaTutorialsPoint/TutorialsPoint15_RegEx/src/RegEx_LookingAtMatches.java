import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx_LookingAtMatches {

    private static final String REGEX = "foo";
    private static final String INPUT = "fooooooooooooooooo";

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(INPUT);

        System.out.println("Current REGEX is: "+REGEX);
        System.out.println("Current INPUT is: "+INPUT);

        System.out.println("lookingat(): "+matcher.lookingAt());
        System.out.println("matches(): "+matcher.matches());
    }
}
