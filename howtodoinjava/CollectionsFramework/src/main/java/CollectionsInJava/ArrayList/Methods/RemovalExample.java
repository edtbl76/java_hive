package CollectionsInJava.ArrayList.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RemovalExample {

    public static void main(String[] args) {
        ArrayList<String> colors = new ArrayList<>(Arrays.asList("Red", "Boogers", "Blue", "Green", "Nebraska"));
        System.out.println(colors);

        // removes member
        colors.remove("Nebraska");
        System.out.println(colors);

        // Ignores missing component
        colors.remove("Omaha");
        System.out.println(colors);

        colors.remove(1);
        System.out.println(colors);


        ArrayList<String> alphabets = new ArrayList<>(Arrays.asList("A", "B", "A","D","A"));
        System.out.println(alphabets);
        alphabets.removeAll(Collections.singleton("A"));
        System.out.println(alphabets);


    }

    public static class adding {
        public static void main(String[] args) {
            // Use type safe Generic syntax
            ArrayList<String> names = new ArrayList<>();

            // normal add, is an append action
            names.add("Paul");

            // "insert" at index.
            names.add(0, "Edward");
            System.out.println(names);

            List<String> names2 = Arrays.asList("Anthony", "Mangini");
            names.addAll(names2);
            System.out.println(names);

            /*
                Example 2:
                    - Inserting a Collection into an arraylist
             */
            ArrayList<String> firstLast = new ArrayList<>(Arrays.asList("Edward","Mangini"));
            List<String> middles = Arrays.asList("Paul", "Anthony");
            firstLast.addAll(1, middles);
            System.out.println(firstLast);
        }
    }
}
