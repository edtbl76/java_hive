package javaeight.timeapi;

import utils.Generated;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.*;
import static java.time.Month.OCTOBER;
import static java.time.temporal.ChronoUnit.MONTHS;

@Generated
@SuppressWarnings("java:S106")
public class DemoLocalDate {

    public static void main(String[] args) {

        //1. get current date
        LocalDate now = now();
        System.out.println(now);

        //2. get specific date w/ of()
        LocalDate date = of(1976, 10, 15);
        System.out.println(date);

        date = of(1981, OCTOBER, 29);
        System.out.println(date);

        //3. get specific date w/ parse()
        date = parse("2000-11-19");
        System.out.println(date);

        date = parse("07/25/2011", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(date);

        // 4. adding days /months to given date
        date = date.plusDays(365);
        System.out.println(date);

        date = date.plus(5, MONTHS);
        System.out.println(date);

        // 5. Get day of week.
        DayOfWeek edwardDay = parse("1976-10-15").getDayOfWeek();
        System.out.println(edwardDay);

        // 6. check if before after
        LocalDate edward = parse("1976-10-15");
        LocalDate vanessa = parse("1981-10-29");
        boolean isBefore = edward.isBefore(vanessa);
        boolean isAfter = edward.isAfter(vanessa);
        System.out.println("Ed is older than vanessa: " + isBefore);
        System.out.println("Ed is younger than vanessa: " + isAfter);


    }
}
