package CollectionsInJava.LinkedHashMap;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class BasicExample1 {

    public static void main(String[] args) {
        LinkedHashMap<Integer, String> pairs = new LinkedHashMap<>();

        pairs.put(1, "A");
        pairs.put(2, "B");
        pairs.put(3, "C");

        String value = pairs.get(3);
        System.out.println(value);

        value = pairs.getOrDefault(5, "ooops");
        System.out.println(value);

        // Iteration example using forEach.
        for (Integer key : pairs.keySet()) {
            System.out.println("Key: " + key + ", Value: " + pairs.get(key));
        }

        // I prefer this syntax.
        pairs.forEach((key, this_value) -> System.out.println("Key: " + key + ", Value: " + this_value));

        // Remove something
        pairs.remove(3);
        System.out.println(pairs);

        System.out.println(pairs.containsKey(1));       // containsKey
        System.out.println(pairs.containsValue("B"));   // containsValue
    }
}
