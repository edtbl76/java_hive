import java.util.*;

public class SortedMapInterfaceDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        TreeMap treeMap = new TreeMap();

        treeMap.put("Zara", 3434.34);
        treeMap.put("Mahnaz", 123.22);
        treeMap.put("Ayan", 1378.00);
        treeMap.put("Daisy", 99.22);
        treeMap.put("Qadir", -19.08);

        Set set = treeMap.entrySet();

        for (Object o: set) {
            Map.Entry me = (Map.Entry)o;
            System.out.println(me.getKey() + ": " + me.getValue());
        }
        System.out.println();

        // add 1000
        double balance = ((Double)treeMap.get("Zara"));
        treeMap.put("Zara", balance + 1000);
        System.out.println("New Balance: " + treeMap.get("Zara"));

    }
}
