package CollectionsInJava.HashMap;

import java.util.HashMap;
import java.util.Iterator;

public class HashMapIteration {

    /*
        NOTE:
            - iterators of HashMaps are fail-fast.
            - structural modifications made after the creation of the iterator will result in
                ConcurrentModificationException
     */
    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        // Old school iterator
        System.out.println("Classic Iterator: ");
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            String value = map.get(key);
            System.out.println("Key: " + key + " Value: " + value);
        }

        // ForEach + Lambda.
        map.forEach(((integer, s) -> {
            System.out.println("Key: " + integer + " Value: " + s);
        }));

    }
}
