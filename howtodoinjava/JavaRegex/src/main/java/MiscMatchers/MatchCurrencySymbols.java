package MiscMatchers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchCurrencySymbols {

    public static void main(String[] args) {
        String currency  = "Let's find the symbols or currencies : $ Dollar, € Euro, ¥ Yen";

        String regex = "\\p{Sc}";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(currency);

        while(matcher.find()) {
            System.out.print("Start index: " + matcher.start());
            System.out.print(" End index: " + matcher.end() + " ");
            System.out.println(matcher.group());
        }
    }
}
