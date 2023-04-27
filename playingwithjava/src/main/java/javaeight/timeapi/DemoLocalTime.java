package javaeight.timeapi;

import utils.Generated;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalTime.*;
import static java.time.temporal.ChronoUnit.MINUTES;

@Generated
@SuppressWarnings("java:S106")
public class DemoLocalTime {

    public static void main(String[] args) {

        //1. current
        LocalTime now = now();
        System.out.println(now);

        //2. of()
        LocalTime ofDemo = of(12, 59);
        System.out.println(ofDemo);

        ofDemo = of(13, 0, 15);
        System.out.println(ofDemo);

        ofDemo = of(13, 2, 21, 156);
        System.out.println(ofDemo);


        //3. parse()
        LocalTime parsedFromCharacterSequence = parse("13:03");
        System.out.println(parsedFromCharacterSequence);

        parsedFromCharacterSequence = parse("13:05", DateTimeFormatter.ofPattern("HH:mm"));
        System.out.println(parsedFromCharacterSequence);

        //4. adding time
        now = now.plusSeconds(20);
        System.out.println(now);

        now = now.plusMinutes(15);
        System.out.println(now);

        now = now.plusHours(4);
        System.out.println(now);

        now = now.plus(31, MINUTES);
        System.out.println(now);

        //4. get minute
        LocalTime current = now();
        System.out.println(current.getMinute());

        //5. before / after
        boolean isBefore = current.isBefore(now);
        boolean isAfter = current.isAfter(now);

        System.out.println("Is " + current + " before " + now + " ? " + isBefore);
        System.out.println("Is " + current + " after " + now + " ? " + isAfter);





    }
}
