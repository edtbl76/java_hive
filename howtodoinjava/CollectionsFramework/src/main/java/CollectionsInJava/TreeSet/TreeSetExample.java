package CollectionsInJava.TreeSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TreeSetExample {

    public static void main(String[] args) {

        // init and print
        TreeSet<String> set = new TreeSet<>(Arrays.asList("A", "B", "C", "D", "E"));
        System.out.println(set);

        // contains()
        System.out.println(set.contains("A"));

        // remove and print.
        set.remove("D");
        System.out.println(set);

        // print via foreach iteration
        set.forEach(System.out::println);

        /*
            Convert to array
         */
        String[] values = new String[set.size()];
        set.toArray(values);
        System.out.println(Arrays.toString(values));

        /*
            Convert to ArrayList
         */
        List<String> list = new ArrayList<>(set);
        System.out.println(list);

        // Not sure why anyone does this....
        List<String> list2 = set.stream().collect(Collectors.toList());
    }
}
