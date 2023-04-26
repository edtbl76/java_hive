package CollectionsInJava.HashSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HashSetToArrayList {

    public static void main(String[] args) {

        // Set up HashSet
        HashSet<Integer> set = new HashSet<>();
        IntStream.range(1,6).forEach(set::add);
        System.out.println(set);

        // Do the conversion
        /*
            Sometimes you'll see this, but its unnecessary
                List<Integer> values = set.stream().collect(Collectors.toList());
         */
        List<Integer> values = new ArrayList<>(set);
        System.out.println(values);

        // This is a VERY brief version using anonymously instantiated ArrayList
        System.out.println(new ArrayList<>(set));

    }
}
