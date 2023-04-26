package CollectionsInJava.ArrayList.Examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class InitializationExample {

    public static void main(String[] args) {

        // Single Statement
        ArrayList<String> shapes = new ArrayList<>(Arrays.asList("square", "circle", "triangle"));
        System.out.println(shapes);

        // Create a list and add objects
        ArrayList<String> twins = new ArrayList<>();
        twins.add("Castor");
        twins.add("Pollux");
        System.out.println(twins);

        // Add elements from another collection
        HashMap<String, Integer> details = new HashMap<>();
        details.put("A", 1);
        details.put("B", 2);
        details.put("C", 3);
        ArrayList<String> keys = new ArrayList<>(details.keySet());
        System.out.println(keys);


    }
}
