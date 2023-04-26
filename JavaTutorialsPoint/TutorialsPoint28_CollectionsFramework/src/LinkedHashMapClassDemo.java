import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapClassDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        LinkedHashMap linkedHashMap = new LinkedHashMap();

        linkedHashMap.put("Doug", 10.10);
        linkedHashMap.put("June", 11.12);
        linkedHashMap.put("Zeke", 12.34);
        linkedHashMap.put("Jasper", 71.98);
        linkedHashMap.put("Simon", 900.8);

        Set set = linkedHashMap.entrySet();

        for (Object o : set) {
            Map.Entry me = (Map.Entry)o;
            System.out.println(me.getKey() + ": " + me.getValue());
        }

        // Make a wish!
        double balance = (Double)linkedHashMap.get("Doug");
        linkedHashMap.put("Doug", balance + 1.01);
        System.out.println("Make a wish!: " + linkedHashMap.get("Doug"));


    }
}
