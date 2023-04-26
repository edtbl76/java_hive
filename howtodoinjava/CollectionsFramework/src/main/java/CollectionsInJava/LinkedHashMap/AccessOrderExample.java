package CollectionsInJava.LinkedHashMap;

import java.util.LinkedHashMap;

public class AccessOrderExample {

    public static void main(String[] args) {
        LinkedHashMap<Integer, String> pairs = new LinkedHashMap<>(2, .75f, true);

        pairs.put(1, "A");
        pairs.put(2, "B");
        pairs.put(3, "C");
        pairs.put(4, "D");

        // mess up access order.
        pairs.get(3);
        pairs.getOrDefault(2, "oops");

        // print'em out.
        pairs.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));


    }
}
