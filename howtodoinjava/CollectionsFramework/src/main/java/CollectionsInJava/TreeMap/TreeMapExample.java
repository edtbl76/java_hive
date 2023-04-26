package CollectionsInJava.TreeMap;

import java.util.Iterator;
import java.util.TreeMap;

public class TreeMapExample {

    public static void main(String[] args) {

        // Natural Ordering By Default
        TreeMap<Integer, String> pairs = new TreeMap<>();
        pairs.put(2, "B");
        pairs.put(1, "A");
        pairs.put(3, "C");
        System.out.println(pairs);  //  NOTE: ignores insertion order, prints them in "natural order, least to greatest"

        // Get a value
        System.out.println(pairs.get(3));
        System.out.println(pairs.getOrDefault(5, "I got nuthin'"));

        // Iteration old school
        Iterator<Integer> iterator = pairs.keySet().iterator();
        while(iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.println("Key: " + key + ", Value: " + pairs.get(key));
        }

        // Iteration forEach
        pairs.forEach((key, value) -> {
            System.out.println("Key: " + key + ", Value: " + value);
        });
    }
}
