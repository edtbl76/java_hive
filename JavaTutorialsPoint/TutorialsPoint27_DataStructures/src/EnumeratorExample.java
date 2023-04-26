import java.util.Vector;
import java.util.Enumeration;

@SuppressWarnings("unchecked")
public class EnumeratorExample {

    public static void main(String[] args) {

        // Remember... this is obsolete for new code.
        Enumeration days;
        Vector dayNames = new Vector();

        dayNames.add("Sunday");
        dayNames.add("Monday");
        dayNames.add("Tuesday");
        dayNames.add("Wednesday");
        dayNames.add("Thursday");
        dayNames.add("Friday");
        dayNames.add("Saturday");

        days = dayNames.elements();

        while (days.hasMoreElements()) {
            System.out.println(days.nextElement());
        }
    }
}
