import java.util.Date;

public class BooleanExample {
    public static void main(String[] args) {

        /*
            Create a boolean type that is set to true if the epoch time is even.
         */
        boolean result = (new Date().getTime() % 2 == 0);

        /*
            Ternary Operator to determine which message to print based on the result stored in the
            boolean variable
         */
        String msg = (result) ? "Even Steven" : "Odd Maude";

        // Self Explanatory...
        System.out.println(msg);
    }
}
