package MiscMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchISBN_10or13 {

    public static void main(String[] args) {

        List<String> isbns = Arrays.asList(
                //Valid ISBNs
                "ISBN 978-0-596-52068-7",
                "ISBN-13: 978-0-596-52068-7",
                "978 0 596 52068 7",
                "9780596520687",
                "0-596-52068-9",
                "0 512 52068 9",
                "ISBN-10 0-596-52068-9",
                "ISBN-10: 0-596-52068-9",

                //Invalid ISBNs
                "ISBN 11978-0-596-52068-7",
                "ISBN-12: 978-0-596-52068-7",
                "978 10 596 52068 7",
                "119780596520687",
                "0-5961-52068-9",
                "11 5122 52068 9",
                "ISBN-11 0-596-52068-9",
                "ISBN-10- 0-596-52068-9"
        );

        String regex = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";

        Pattern pattern = Pattern.compile(regex);

        isbns.forEach(isbn -> {
            Matcher matcher = pattern.matcher(isbn);
            System.out.println(isbn  + " : " +  matcher.matches());
        });
    }
}
