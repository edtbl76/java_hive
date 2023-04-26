package PostalCodes;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CanadianPostalCode {

    public static void main(String[] args) {
        List<String> zips = Arrays.asList(
                // VALID
                "K1A 0B1",
                "B1Z 0B9",

                // INVALID
                "K1A 0D1",
                "W1A 0B1",
                "Z1A 0B1"
        );
        String regex = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";

        Pattern pattern = Pattern.compile(regex);
        zips.forEach(zip -> {
            Matcher matcher = pattern.matcher(zip);
            System.out.println(zip + " : " + matcher.matches());
        });
    }
}
