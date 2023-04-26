package CollectionsInJava.HashMap.Examples;

import CollectionsInJava.HashMap.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;

public class HashMapDeepClone {

    public static void main(String[] args) {

        // Initial HM
        HashMap<Integer, Person> map = new HashMap<>();
        map.put(1, new Person(1, "Ed", "Mangini", LocalDate.of(1976, 10, 15)));
        map.put(2, new Person(1, "Vanessa", "Underwood", LocalDate.of(1981, 10, 29)));
        System.out.println(map);

        // Deep Clone.
        Gson gson = new Gson();
        String json = gson.toJson(map);

        Type type = new TypeToken<HashMap<Integer, Person>>(){}.getType();
        HashMap<Integer, Person> clonedMap = gson.fromJson(json, type);

        // Make the changes
        clonedMap.get(2).setLastName("Mangini");

        System.out.println(map);        // Changes are NOT reflected in the original map.
        System.out.println(clonedMap);
    }
}
