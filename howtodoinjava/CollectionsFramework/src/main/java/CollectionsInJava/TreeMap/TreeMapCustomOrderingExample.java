package CollectionsInJava.TreeMap;

import java.util.Collections;
import java.util.TreeMap;

public class TreeMapCustomOrderingExample {

    public static void main(String[] args) {

        // Start my sorting in reverse order
        TreeMap<Integer, String> pairs = new TreeMap<>(Collections.reverseOrder());
        pairs.put(2, "B");
        pairs.put(1, "A");
        pairs.put(3, "C");
        System.out.println(pairs);
    }
}
