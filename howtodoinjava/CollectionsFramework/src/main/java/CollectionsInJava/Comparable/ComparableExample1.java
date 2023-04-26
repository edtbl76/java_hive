package CollectionsInJava.Comparable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ComparableExample1 {

    public static void main(String[] args) {
        // Strings.
        ArrayList<String> list = new ArrayList<>(Arrays.asList("E", "A", "C", "B", "D"));
        System.out.println("Unsorted: " + list);

        Collections.sort(list);
        System.out.println("Sorted: " + list);

        list.sort(Collections.reverseOrder());
        System.out.println("Reversed: "+ list);

        // Ints
        ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(10, 300, 45, 2, 5));
        System.out.println("Unsorted: " + ints);

        Collections.sort(ints);
        System.out.println("Sorted: " + ints);

        ints.sort(Collections.reverseOrder());
        System.out.println("Reversed: " + ints);

        // Objects
        ArrayList<Person> people = new ArrayList<>();

        people.add(new Person((long)3, "Mike"));
        people.add(new Person((long)2, "Vanessa"));
        people.add(new Person((long)4, "Connor"));
        people.add(new Person((long)1, "Ed"));

        System.out.println("PreSort: " + people);

        Collections.sort(people);
        System.out.println("Natural: " + people);

        people.sort(Collections.reverseOrder());
        System.out.println("Reversed: " + people);

    }
}
