package EmailMatching;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailMatcher5_recommended {

    public static void main(String[] args) {
        List<String> emails = Arrays.asList(
                "user@domain",
                "user@domain.co.in",
                "user.name@domain.com",
                "user_name@domain.com",
                "username@domain.corporate.in",
                ".username@domain.com",
                "username@domain.com.",
                "username@domain..com",
                "username@domain.c",
                "username@domain.corporate"
        );

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);
        emails.forEach(email -> {
            Matcher matcher = pattern.matcher(email);
            System.out.println(email + " : \t" + matcher.matches());
        });
    }
}
