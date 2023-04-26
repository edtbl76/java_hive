package CollectionsInJava.HashMap.Examples;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class SyncdHashMap1_ConcurrentHashMap {

    /*
        ConcurrentHashMap is the recommended implementation of a Map in concurrent situations.
        - access to kv pairs are thread safe by design.

        ITERATORS from ConcurrentHashMap do NOT through ConcurrentModificationException
            - i.e. the iterator is NOT thread safe.

        ITERATOR is guaranteed too REFLECT THE STATE OF THE MAP AT THE TIME OF ITS CREATION
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();

        // Put requires no synchronization
        concurrentHashMap.put(1, "A");
        concurrentHashMap.put(2, "B");

        // Get requires no synchronization
        concurrentHashMap.get(1);

        Iterator<Integer> iterator = concurrentHashMap.keySet().iterator();

        // use sync is advised here.
        synchronized (concurrentHashMap) {
            while(iterator.hasNext()) {
                System.out.println(concurrentHashMap.get(iterator.next()));
            }
        }

    }
}
