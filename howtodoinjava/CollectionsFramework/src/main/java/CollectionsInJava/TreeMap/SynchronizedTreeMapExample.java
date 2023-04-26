package CollectionsInJava.TreeMap;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class SynchronizedTreeMapExample {

    public static void main(String[] args) {
        Map<Integer, String> syncTreeMap = Collections.synchronizedSortedMap(new TreeMap<>());
        syncTreeMap.put(1, "A");
        syncTreeMap.put(2, "B");
        syncTreeMap.put(3, "C");
        System.out.println(syncTreeMap);
    }
}
