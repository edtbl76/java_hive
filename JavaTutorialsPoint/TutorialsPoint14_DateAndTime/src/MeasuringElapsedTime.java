import java.util.*;

public class MeasuringElapsedTime {

    public static void main(String[] args) {
        try {
            // Set start ms
            long start = System.currentTimeMillis();

            // Date, Sleep , Date
            System.out.println(new Date() + "\n");
            Thread.sleep(5*60*10); // 3000 ms = 3 seconds
            System.out.println(new Date() + "\n");

            // Set fin ms
            long end = System.currentTimeMillis();

            // Calc diff and output
            long diff = end - start;
            System.out.println("Difference is: " + diff);

        } catch (Exception e) {
            System.out.println("Oops! Exception!");
        }
    }
}
