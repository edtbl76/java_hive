package MiscMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchISBN_13 {

    public static void main(String[] args) {
        List<String> isbns = Arrays.asList(
                "ISBN 978-0-596-52068-7",
                "ISBN-13: 978-0-596-52068-7",
                "978 0 596 52068 7",
                "9780596520687",
                "ISBN 11978-0-596-52068-7",
                "ISBN-12: 978-0-596-52068-7",
                "978 10 596 52068 7",
                "119780596520687"
        );

        String regex = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$";

        Pattern pattern = Pattern.compile(regex);
        isbns.forEach(isbn -> {
            Matcher matcher = pattern.matcher(isbn);
            System.out.println(isbn + " : " + matcher.matches());
        });
    }
}
