package CollectionsInJava.HashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapIteration_EntrySets {

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        // Iterating over entries Set
        Iterator<Map.Entry<Integer, String>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Integer, String> entry = entryIterator.next();

            System.out.println("The key is " + entry.getKey() + ", and value is " + entry.getValue());
        }

        // easier
        map.forEach((key, value) ->
            System.out.println("The key is " + key + ", and value is " + value)
        );
    }
}
