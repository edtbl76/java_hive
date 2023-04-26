package PhoneNumbers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InternationalPNExample2_EPPFormat {

    public static void main(String[] args) {
        List<String> phoneNums = Arrays.asList(
                "+123.123456x4444",
                "+12.1234x11",
                "+1.123456789012x123456789"
        );

        String regex = "^\\+[0-9]{1,3}\\.[0-9]{4,14}(?:x.+)?$";
        Pattern pattern = Pattern.compile(regex);
        phoneNums.forEach(number -> {
            Matcher matcher = pattern.matcher(number);
            System.out.println(number + " : " + matcher.matches());
        });
    }
}
