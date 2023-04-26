import java.util.*;

public class MapEntryInterfaceDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        // Create HashMap
        HashMap hashMap = new HashMap();

        // Add elements
        hashMap.put("Buddy", 10.10);
        hashMap.put("Caspar", 100.9);
        hashMap.put("Wendy", 1000.11);
        hashMap.put("Frederick", 11.03);
        hashMap.put("Wesley", 12.7);

        Set set = hashMap.entrySet();

        for (Object o : set) {
            Map.Entry me = (Map.Entry) o;
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }

    }
}
