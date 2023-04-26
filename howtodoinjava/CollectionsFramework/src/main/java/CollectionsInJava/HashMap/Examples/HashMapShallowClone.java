package CollectionsInJava.HashMap.Examples;

import CollectionsInJava.HashMap.Person;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;

public class HashMapShallowClone {

    public static void main(String[] args) {
        HashMap<Integer, Person> personMap = new HashMap<>();

        personMap.put(1, new Person(1, "Ed", "Mangini", LocalDate.of(1976, Month.OCTOBER, 15)));
        personMap.put(2, new Person(2, "Vanessa", "Underwood", LocalDate.of(1981, Month.OCTOBER, 29)));
        System.out.println(personMap);

        // shallow clone
        HashMap<Integer, Person> clonedMap = (HashMap<Integer, Person>) personMap.clone();

        // change value in clonedMap
        clonedMap.get(2).setLastName("Mangini");

        System.out.println(personMap);
        System.out.println(clonedMap);
    }
}


