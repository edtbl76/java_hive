package PostalCodes;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class USZipCodeExample1 {

    public static void main(String[] args) {

        List<String> zips = Arrays.asList(
                // Valid
                "12345",
                "12345-6789",

                // Invalid
                "123456",
                "1234",
                "12345-678",
                "12345-67890"
        );
        String regex = "^[0-9]{5}(?:-[0-9]{4})?";

        Pattern pattern = Pattern.compile(regex);

        zips.forEach(zip -> {
            Matcher matcher = pattern.matcher(zip);
            System.out.println(zip + " : " + matcher.matches());
        });
    }
}
