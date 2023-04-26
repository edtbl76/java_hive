package DateFormats;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatMatch4_ddmmyyyy_LeadZeroesRequired {

    public static void main(String[] args) {
        List<String> dates = Arrays.asList(
                "1/1/11",
                "01/01/11",
                "01/01/2011",
                "01/1/2011",
                "1/11/2011",
                "1/11/11",
                "11/1/11"
        );

        String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);

        dates.forEach(date -> {
            Matcher matcher = pattern.matcher(date);
            System.out.println(date + " : " + matcher.matches());
        });
    }
}
