package javaeight.timeapi;

import utils.Generated;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.*;
import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;
import static java.time.Month.OCTOBER;
import static java.time.temporal.ChronoUnit.MONTHS;

@Generated
@SuppressWarnings("java:S106")
public class DemoLocalDateTime {

    public static void main(String[] args) {

        //1. current
        LocalDateTime now = now();
        System.out.println(now);

        //2. of()
        LocalDateTime dateTime = of(now.getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(), now.getMinute());
        System.out.println(dateTime);

        dateTime = of(now.getYear(), OCTOBER, now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond());
        System.out.println(dateTime);

        //3. parse()
        LocalDateTime date = parse("2020-06-20T07:54:00");
        System.out.println(date);

        // modifying
        date = date.plusDays(4);
        System.out.println(date);

        date = date.plus(4, MONTHS);
        System.out.println(date);

        date = date.minusMonths(4);
        System.out.println(date);



    }

}
