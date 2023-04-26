package CollectionsInJava.HashMap;

import java.util.HashMap;

public class HashMapPutExample {

    public static void main(String[] args) throws CloneNotSupportedException {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "one");
        map.put(2, "two");

        System.out.println(map);
    }
}
