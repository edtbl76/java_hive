package MiscMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchCreditCards {

    public static void main(String[] args) {
        List<String> cards = Arrays.asList(
                "xxxx-xxxx-xxxx-xxxx",
                "4294-9999-9999-9999"
                );

        String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
                "(?<mastercard>5[1-5][0-9]{14})|" +
                "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
                "(?<amex>3[47][0-9]{13})|" +
                "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
                "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";

        Pattern pattern = Pattern.compile(regex);

        cards.forEach(card -> {
            // string hyphens
            card = card.replaceAll("-", "");

            Matcher matcher = pattern.matcher(card);
            System.out.println(card + " : " + matcher.matches());
            if (matcher.matches()) {
                System.out.println(matcher.group("visa"));
            }
        });
    }

}
