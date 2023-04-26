package EmailMatching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailMatcher3_RFC5322 {

    public static void main(String[] args) {

        List<String> emails = new ArrayList<>(Arrays.asList(
                "user@domain.com",
                "user@domain.co.in",
                "user.name@domain.com",
                "user?name@domain.co.in",
                "user'name@domain.co.in",
                "user#@domain.co.in",
                "user@domaincon",
                "user#domain.com",  // invalid
                "@domain.com"       // invalid
        ));

        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        emails.forEach(email -> {
            Matcher matcher = pattern.matcher(email);
            System.out.println(email + " : " + matcher.matches());
        });
    }
}
