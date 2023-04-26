package MiscMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchSSN {

    public static void main(String[] args) {
        List<String> ssns = Arrays.asList(
                "123-45-6789",
                "856-45-6789",
                "000-45-6789",
                "666-45-6789",
                "901-45-6789",
                "85-345-6789",
                "856-453-6789",
                "856-45-6789",
                "856-456789"
        );

        String regex = "^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$";

        Pattern pattern = Pattern.compile(regex);
        ssns.forEach(ssn -> {
            Matcher matcher = pattern.matcher(ssn);
            System.out.println(ssn + " : " + matcher.matches());
        });
    }
}
