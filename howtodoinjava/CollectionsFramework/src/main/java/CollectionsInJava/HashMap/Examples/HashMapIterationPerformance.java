package CollectionsInJava.HashMap.Examples;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapIterationPerformance {

    static HashMap<String, Integer> testMap = new HashMap<>();

    static {
        for (int i = 0; i < 1000000; i++)
            testMap.put("key_" + i, i);
    }


    public static void main(String[] args) {

        // entrySet() forEach
        long start = Calendar.getInstance().getTimeInMillis();
        testMap.entrySet().forEach(stringIntegerEntry -> {
            stringIntegerEntry.getKey();
            stringIntegerEntry.getValue();
        });

        System.out.println("Using entrySet() in for-each loop: " +
                (Calendar.getInstance().getTimeInMillis() - start));


        // keySet() forEach
        start = Calendar.getInstance().getTimeInMillis();
        testMap.keySet().forEach(key -> testMap.get(key));
        System.out.println("Usinge keySet() in for-each loop: " +
                (Calendar.getInstance().getTimeInMillis() - start));

        // Iterator on entrySet in while loop
        Iterator<Map.Entry<String, Integer>> iterator = testMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            entry.getKey();
            entry.getValue();
        }
        System.out.println("Using entrySet() and Iterator: " +
                (Calendar.getInstance().getTimeInMillis() - start));

        // Key set w/ iterator
        Iterator<String> stringIterator = testMap.keySet().iterator();
        while (stringIterator.hasNext()) {
            String key = stringIterator.next();
            testMap.get(key);
        }
        System.out.println("Using keySet() and Iterator: " +
                (Calendar.getInstance().getTimeInMillis() - start));


    }
}
