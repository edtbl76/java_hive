import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("Duplicates")
public class IdentityHashMapClassDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        LinkedHashMap linkedHashMap = new LinkedHashMap();

        linkedHashMap.put("Bentley", 10.10);
        linkedHashMap.put("Geoff", 11.12);
        linkedHashMap.put("Oswald", 12.34);
        linkedHashMap.put("Rod", 71.98);
        linkedHashMap.put("Ned", 900.8);

        Set set2 = linkedHashMap.entrySet();

        for (Object ob : set2) {
            Map.Entry mapentry = (Map.Entry)ob;
            System.out.println(mapentry.getKey() + ": " + mapentry.getValue());
        }

        // Make a wish!
        double balance = (Double)linkedHashMap.get("Bentley");
        linkedHashMap.put("Bentley", balance + 1.01);
        System.out.println("Make a wish!: " + linkedHashMap.get("Bentley"));


    }
}
