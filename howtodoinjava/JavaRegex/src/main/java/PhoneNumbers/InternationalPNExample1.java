package PhoneNumbers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InternationalPNExample1 {
    /*

        ^       beginning of the string
        \+      match literal "+"
        (?:     group, but don't capture
        [0-9]   match a digit
        \\s     match a space character
        ?       match 1 or 0 times
        )       end non-capture group
        {6,14}  repeat between 6 and 14 times
        [0-9]   still matches a digit
        #       asserts position at end of the string.

     */
    public static void main(String[] args) {

        List<String> phoneNums = Arrays.asList(
                "+1 1234567890123",
                "+12 123456789",
                "+123 123456"
        );

        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(regex);
        phoneNums.forEach(number -> {
            Matcher matcher = pattern.matcher(number);
            System.out.println(number + " : " + matcher.matches());
        });
    }
}
