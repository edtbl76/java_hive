package MiscMatchers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchGreekAndGreekExtended {

    public static void main(String[] args) {
        String greek = "A math equation might be α + β = λ + γ";
        String greekExtended = "Let's learn some new greek extended characters : ᾲ , ᾨ etc.";

        String regex_g = "\\p{InGreek}";
        String regex_ge = "\\p{InGreekExtended}";

        Pattern pattern = Pattern.compile(regex_g, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(greek);
        matchLoop(matcher);

        matcher.reset();
        pattern = Pattern.compile(regex_ge,Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(greekExtended);
        matchLoop(matcher);


    }

    private static void matchLoop(Matcher matcher) {
        while(matcher.find()) {
            System.out.print("Start Index: " + matcher.start());
            System.out.print(" End Index: " + matcher.end() + " ");
            System.out.println(matcher.group());
        }
    }
}
