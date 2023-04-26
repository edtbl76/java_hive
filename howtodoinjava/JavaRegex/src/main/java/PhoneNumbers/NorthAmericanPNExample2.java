package PhoneNumbers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NorthAmericanPNExample2 {

    public static void main(String[] args) {

        List<String> phoneNumbers = Arrays.asList(
                "1234567890",
                "123-456-7890",
                "123.456.7890",
                "123 456 7890",
                "(123) 456 7890",
                "12345678",
                "12-12-111"
        );

        String regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
        Pattern pattern = Pattern.compile(regex);
        phoneNumbers.forEach(number -> {
            Matcher matcher = pattern.matcher(number);

            // if it is correct, let's standardize the way it is stored. (good old fashioned data cleansing)
            if (matcher.matches())
                System.out.println(matcher.replaceFirst("($1) $2-$3"));
        });
    }
}
