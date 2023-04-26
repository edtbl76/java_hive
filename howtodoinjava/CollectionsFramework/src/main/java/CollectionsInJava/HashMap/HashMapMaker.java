package CollectionsInJava.HashMap;

import java.util.HashMap;

public class HashMapMaker {

    private static HashMap<Integer, String> map = new HashMap<>();

    public HashMapMaker() {
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
    }

    public static HashMap<Integer, String> getMap() {
        if (map.isEmpty()) {
            map.put(1, "one");
            map.put(2, "two");
            map.put(3, "three");
            map.put(4, "four");
            map.put(5, "five");
        }
        return map;
    }
}
