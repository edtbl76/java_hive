package CollectionsInJava.LinkedHashSet;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class LinkedHashSetToArray {

    public static void main(String[] args) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>(Arrays.asList(1,2,3,4,5));
        System.out.println(set);

        // Convert to an array
        Integer[] ints = new Integer[set.size()];
        set.toArray(ints);
        System.out.println(Arrays.toString(ints));
    }
}
