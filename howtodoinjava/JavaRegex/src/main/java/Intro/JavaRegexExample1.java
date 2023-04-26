package Intro;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaRegexExample1 {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Ed|Vanessa");
        Matcher matcher = pattern.matcher("Generally, Ed and Vanessa have nothing in common!");

        while(matcher.find()) {
            System.out.print("Start Index: " + matcher.start());
            System.out.print(" End Index: " + matcher.end());
            System.out.println(" - " + matcher.group());
        }
    }
}
