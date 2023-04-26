package CollectionsInJava.HashMap.Examples;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SyncdHashMap2_synchronizedMap {

    public static void main(String[] args) {
        Map<Integer, String> syncHashMap = Collections.synchronizedMap(new HashMap<>());

        // no sync on puts
        syncHashMap.put(1, "A");
        syncHashMap.put(2, "B");

        // GET no sync
        syncHashMap.get(1);

        Iterator<Integer> iterator = syncHashMap.keySet().iterator();

        synchronized(syncHashMap) {
            while (iterator.hasNext())
                System.out.println(syncHashMap.get(iterator.next()));
        }
    }
}
