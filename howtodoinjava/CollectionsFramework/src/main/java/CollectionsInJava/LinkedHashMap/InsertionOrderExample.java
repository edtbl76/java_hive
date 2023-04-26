package CollectionsInJava.LinkedHashMap;

import java.util.LinkedHashMap;

public class InsertionOrderExample {

    public static void main(String[] args) {
        LinkedHashMap<Integer, String> pairs = new LinkedHashMap<>();
        pairs.put(1, "A");
        pairs.put(2, "B");
        pairs.put(3, "C");
        pairs.put(4, "D");

        pairs.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
    }
}
