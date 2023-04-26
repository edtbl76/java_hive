package MiscMatchers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchTrademark {

    public static void main(String[] args) {
        String trademark = "Searching in trademark character ™ is so easy when you know it.";

        String regex = "\u2122";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(trademark);

        while(matcher.find()) {
            System.out.print("Start index: " + matcher.start());
            System.out.print(" End index: " + matcher.end() + " ");
            System.out.println(matcher.group());
        }

    }
}
