package MiscMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchISBN_10 {

    public static void main(String[] args) {
        List<String> isbns = Arrays.asList(
                "0-596-52068-9",
                "0 512 52068 9",
                "ISBN-10 0-596-52068-9",
                "ISBN-10: 0-596-52068-9",
                "0-5961-52068-9",
                "11 5122 52068 9",
                "ISBN-13 0-596-52068-9",
                "ISBN-10- 0-596-52068-9"
        );

        String regex = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";

        Pattern pattern = Pattern.compile(regex);
        isbns.forEach(isbn ->  {
            Matcher matcher = pattern.matcher(isbn);
            System.out.println(isbn + " : " + matcher.matches());
        });
    }
}
