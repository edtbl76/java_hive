package javaeight.timeapi;

import utils.Generated;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import static java.time.Duration.*;
import static java.time.Duration.ofDays;
import static java.time.LocalTime.MIDNIGHT;
import static java.time.Period.*;

@Generated
@SuppressWarnings("java:S106")
public class DemoPeriodAndDuration {

    public static final String SECONDS = "Seconds: ";

    public static void main(String[] args) {
        /*
            Period -> data-based amount of time in ISO8601 calendar system.
         */
        Period period = Period.ofDays(5);
        System.out.println(period.getDays());

        period = ofMonths(3);
        System.out.println(period.getMonths());

        period = ofYears(2);
        System.out.println(period.getYears());

        period = of(2, 5, 12);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());

        // Date differentiation
        period = Period.between(LocalDate.parse("1976-10-15"), LocalDate.parse("1981-10-29"));
        System.out.println(period);

        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        System.out.println("The difference between two dates is " + years + " Years, " + months + " Months and " + days + " Days");

        /*
            Duration
         */
        Duration duration = ofDays(1);
        System.out.println(SECONDS + duration.getSeconds());

        duration = ofHours(2);
        System.out.println(SECONDS + duration.getSeconds());

        duration = ofMinutes(23);
        System.out.println(SECONDS + duration.getSeconds());

        duration = of(1, ChronoUnit.HALF_DAYS);
        System.out.println(SECONDS + duration.getSeconds());

        duration = between(LocalTime.now(), MIDNIGHT);
        System.out.println("The difference between now and midnight is " + duration.getSeconds());

    }
}
