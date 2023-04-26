import java.util.Calendar;
import java.util.Date;

public class LogicalOperators {

    public static void main(String[] args) {

        // Initialize some dynamic variables
        long epoch = new Date().getTime();
        long millisecond = Calendar.getInstance().getTimeInMillis();
        //
        String msg = (epoch % 2 == 0) && (millisecond % 2 == 0)
                ? "AND Even Steven"
                : (epoch % 2 == 0) || (millisecond %2 == 0)
                // This slips the NOT operator in.
                    ? !(epoch % 2 == 0)
                        ? "Odd Epoch, Even Millisecond"
                        : "Odd Millisecond, Even Epoch"
                    : "AND Odd Maude";

        System.out.println("Epoch: " + epoch + " Millisecond: " + millisecond + " : " + msg);
    }
}
