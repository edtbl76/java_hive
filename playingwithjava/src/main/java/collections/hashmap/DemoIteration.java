package collections.hashmap;

import utils.Generated;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Generated
@SuppressWarnings({"java:S106", "java:S1192"})
public class DemoIteration {

    public static void main(String[] args) {

        DecimalFormat format = new DecimalFormat("0.00");
        Map<String, Double> map = new HashMap<>(Map.of(
                "Avid", 76.67,
                "Oracle", 21.67,
                "Amazon", 35.17,
                "MorphoTrust", 4.17,
                "Tracelink", 48.83,
                "iRobot", 3.0,
                "OnShape", 5.5,
                "Method", 3.33,
                "Thoughtworks", 19.67

        ));

        // Create a set of entries
        Set<Entry<String, Double>> entrySet = map.entrySet();

        // 1. Iterate w/ forEach loop
        for (Entry<String, Double> entry : entrySet) {
            String years = format.format(entry.getValue() / 12);
            System.out.println("Company: " + entry.getKey() + " Years: " + years);
        }
        System.out.println();

        // 2. Iterator
        /*
            Note if we remove an element from the entrySet IT IS ALSO REMOVED
            FROM THE BACKING MAP.
         */
        Iterator<Entry<String, Double>> iterator = entrySet.iterator();
        while(iterator.hasNext()) {
            Entry<String, Double> entry = iterator.next();
            String years = format.format(entry.getValue() / 12);
            System.out.println("Company: " + entry.getKey() + " Years: " + years);

            if (entry.getKey().equals("iRobot")) {
                iterator.remove();
            }
        }
        // iRobot was removed.
        System.out.println("Original Map: " + map);
        System.out.println();

        // 3. forEach()
        map.forEach((key, value) -> {
           System.out.print("Company: " + key);
           System.out.println("Years: " + format.format(value / 12));
        });




    }
}
