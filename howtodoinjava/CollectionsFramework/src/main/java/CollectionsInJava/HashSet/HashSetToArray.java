package CollectionsInJava.HashSet;

import java.util.Arrays;
import java.util.HashSet;

public class HashSetToArray {

    public static void main(String[] args) {

        // Create HashSet, add some elements, and print it out.
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("A");
        hashSet.add("B");
        hashSet.add("C");
        hashSet.add("D");
        hashSet.add("E");
        System.out.println(hashSet);

        // convert it to array and print it out
        String[] values = new String[hashSet.size()];
        hashSet.toArray(values);
        System.out.println(Arrays.toString(values));
    }
}
