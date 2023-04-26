package PostalCodes;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UKPostalCodeExample {
    public static void main(String[] args) {

        List<String> zips = Arrays.asList(
                // VALID
                "SW1W 0NY",
                "PO16 7GZ",
                "GU16 7HF",
                "L1 8JQ",

                //Invalid ZIP codes
                "Z1A 0B1",
                "A1A 0B11"
        );

        String regex = "^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-JLNP-UW-Z]{2}$";

        Pattern pattern = Pattern.compile(regex);
        zips.forEach(zip -> {
            Matcher matcher =  pattern.matcher(zip);
            System.out.println(zip + " : " + matcher.matches());
        });
    }
}
