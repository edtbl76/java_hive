package CollectionsInJava.HashMap.Examples;

import CollectionsInJava.HashMap.Person;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HashMapShallowClone2_Java8 {

    public static void main(String[] args) {

        // setup initial map
        HashMap<Integer, Person> map = new HashMap<>();
        map.put(1, new Person(1, "Ed", "Mangini", LocalDate.of(1976, 10, 15)));
        map.put(2, new Person(2, "Vanessa", "Underwood", LocalDate.of(1981, 10, 29)));
        System.out.println(map);

        // do the cloning
        Map<Integer, Person> clonedMap = map.entrySet()
                .stream()
                // perform customization
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Demonstrate SHALLOW clone
        System.out.println(map);
        System.out.println(clonedMap);
    }
}
