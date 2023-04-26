import java.util.*;

public class GregCalendar {

    public static void main(String[] args) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August",
        "September", "October", "November", "December"};

        int year;
        GregorianCalendar gcal = new GregorianCalendar();

        // DATE
        System.out.print("Date: ");
        System.out.print(months[gcal.get(Calendar.MONTH)]);
        System.out.print(" " + gcal.get(Calendar.DATE) + " ");
        System.out.println(year = gcal.get(Calendar.YEAR));

        // TIME
        System.out.print("Time: ");
        System.out.print(gcal.get(Calendar.HOUR) + ":");
        System.out.print(gcal.get(Calendar.MINUTE) + ":");
        System.out.println(gcal.get(Calendar.SECOND));

        //Leap
        if (gcal.isLeapYear(year)) {
            System.out.println(year + " is a leap year.");
        } else {
            System.out.println(year + " is not a leap year.");
        }

    }
}
