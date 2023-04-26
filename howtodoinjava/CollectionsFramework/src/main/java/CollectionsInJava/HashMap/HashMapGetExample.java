package CollectionsInJava.HashMap;

import java.util.HashMap;

public class HashMapGetExample {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        System.out.println("The value at key 1 is " + map.get(1));
    }
}
