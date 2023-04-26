import java.util.Date;

public class DateFormatPrintF {

    public static void main(String[] args) {
        Date date = new Date();

        // uses printf
        String str = String.format("Current Date/Time : %tc", date);
        System.out.println(str);

        // uses an index format
        // index immediately follows the '%' sign, and it is terminated by a $. (The time conversion format follows)
        Date date2 = new Date();
        System.out.printf("%1$s %2$tB %2$td, %2$tY", "Due date:", date2);
        System.out.print("\n");

        //using the < flag, which indicates that the same argument as in the preceding format
        // specification shouldbe used again
        Date date3 = new Date();
        System.out.printf("%s %tB %<te, %<tY", "Due date:", date3);
        System.out.print("\n");

    }
}
