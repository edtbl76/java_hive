import java.util.*;

public class Sleeping {

    public static void main(String[] args) {
        try {
            System.out.println(new Date() + "\n");
            Thread.sleep(5*60*10); // yes 3000 ms = 3 seconds...
            System.out.println(new Date() + "\n");
        } catch (Exception e) {
            System.out.println("Oops. Sumpin' went wrong.");
        }
    }
}
