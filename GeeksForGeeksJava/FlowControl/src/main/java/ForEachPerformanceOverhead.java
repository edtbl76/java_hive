import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SuppressWarnings("unused")
public class ForEachPerformanceOverhead {

    @SuppressWarnings({"UnnecessaryLocalVariable", "ForLoopReplaceableByForEach"})
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        long startTime, endTime;

        // Seed the array
        for (int i = 0; i < 1_000_000; i++) {
            list.add(i);
        }

        // Type 1 :: For Each Loop
        startTime = Calendar.getInstance().getTimeInMillis();
        for (int i : list) {
            int a = i;
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("For each loop :: " + (endTime - startTime) + " ms");

        // Type 2 :: Collection.size()
        startTime = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < list.size(); i++) {
            int a = list.get(i);
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Using collection.size() :: " + (endTime - startTime) + " ms");


        // Type 3 :: Calculating collection.size() outside of the loop (i.e. only once)
        int size = list.size();
        startTime = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < size; i++) {
            int a = list.get(i);
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Pre-calculating list.size :: " + (endTime - startTime) + " ms");

        // Type 4 :: operating from the other end of the data structure
        startTime = Calendar.getInstance().getTimeInMillis();
        for (int i = list.size() - 1; i >= 0; i--) {
            int a = list.get(i);
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Going backwards :: " + (endTime - startTime) + " ms");

    }
}
