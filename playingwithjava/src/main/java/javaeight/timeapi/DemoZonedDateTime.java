package javaeight.timeapi;

import utils.Generated;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.Set;

import static java.time.ZoneId.*;
import static java.time.ZonedDateTime.*;

@Generated
@SuppressWarnings({"java:S106", "java:S1192"})
public class DemoZonedDateTime {

    static Random random = new Random();
    public static void main(String[] args) {

        // get ZoneId for given Zone
        ZoneId zoneId = of("America/New_York");
        System.out.println("Zone Id " + zoneId);

        // get list of Zone Ids
        Set<String> zoneIdList = getAvailableZoneIds();
        zoneIdList.forEach(System.out::println);


        // 2. Creating ZDT instance
        ZonedDateTime zonedDateTime = now();
        System.out.println(zonedDateTime);

        // (use previous zoneId)
        zonedDateTime = of(2023, 3, 24, 13, 50, 0, 0, zoneId);
        System.out.println(zonedDateTime);


        // 3. getDetails
        zonedDateTime = now();
        System.out.println("Year         : " + zonedDateTime.getYear());
        System.out.println("Month        : " + zonedDateTime.getMonth());
        System.out.println("Day of Month : " + zonedDateTime.getDayOfMonth());
        System.out.println("Day of Week  : " + zonedDateTime.getDayOfWeek());
        System.out.println("Day of Year  : " + zonedDateTime.getDayOfYear());
        System.out.println("Hour         : " + zonedDateTime.getHour());
        System.out.println("Minute       : " + zonedDateTime.getMinute());
        System.out.println("Second       : " + zonedDateTime.getSecond());
        System.out.println("Nanosecond   : " + zonedDateTime.getNano());

        // 4. modify!
        int years = random.nextInt(100);
        int months = random.nextInt(12);
        int days = random.nextInt(28);
        int hours = random.nextInt(24);
        int minutes = random.nextInt(60);
        int seconds = random.nextInt(60);
        int nanoseconds = random.nextInt(1000);

        System.out.println("Year         : " + zonedDateTime.plusYears(years));
        System.out.println("Month        : " + zonedDateTime.plusMonths(months));
        System.out.println("Day of Month : " + zonedDateTime.plusDays(days));
        System.out.println("Hour         : " + zonedDateTime.plusHours(hours));
        System.out.println("Minute       : " + zonedDateTime.plusMinutes(minutes));
        System.out.println("Second       : " + zonedDateTime.plusSeconds(seconds));
        System.out.println("Nanosecond   : " + zonedDateTime.plusNanos(nanoseconds));

        System.out.println("Year         : " + zonedDateTime.minusYears(random.nextInt(years)));
        System.out.println("Month        : " + zonedDateTime.minusMonths(random.nextInt(months)));
        System.out.println("Day of Month : " + zonedDateTime.minusDays(random.nextInt(days)));
        System.out.println("Hour         : " + zonedDateTime.minusHours(random.nextInt(hours)));
        System.out.println("Minute       : " + zonedDateTime.minusMinutes(random.nextInt(minutes)));
        System.out.println("Second       : " + zonedDateTime.minusSeconds(random.nextInt(seconds)));
        System.out.println("Nanosecond   : " + zonedDateTime.minusNanos(random.nextInt(nanoseconds)));

    }

}
