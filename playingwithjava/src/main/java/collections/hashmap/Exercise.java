package collections.hashmap;

import utils.Generated;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Generated
@SuppressWarnings({"java:S106", "java:S125"})
public class Exercise {

    public static void main(String[] args) {

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

        // 1. find most years
        AtomicReference<Double> max = new AtomicReference<>(0.0);
        AtomicReference<String> company = new AtomicReference<>("");

        map.forEach((key, value) -> {
            if (value > max.get()) {
                max.set(value);
                company.set(key);
            }
        });

        // 2. compute average
        Double average = map
                .values()
                .stream()
                .reduce(Double::sum)
                .map(dubble -> dubble / map.values().size())
                .orElse(0.0);

        System.out.println("My average tenure is : " + average + " months.");


        System.out.println("I was at " + company + " for " + max +  " months");


        // 3. remove tenures lower than the average.
        /*

            This throws ConcurrentModificationException...

            map.forEach((k, v) -> {
                if (v < average) {
                    map.remove(k);
                }
            });

            ... you have to use an iterator.

            .... or, you can use removeIf on an entry set.
         */

        map.entrySet().removeIf(entry -> entry.getValue() < average);
        System.out.println("Above average tenure: " + map);

    }
}
