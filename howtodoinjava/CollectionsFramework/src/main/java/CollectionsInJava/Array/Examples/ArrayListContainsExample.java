package CollectionsInJava.Array.Examples;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListContainsExample {

    public static void main(String[] args) {

        // Example 1
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Peanut", "Pecan", "Cashew", "Almond"));

        System.out.println(list.contains("Pecan"));
        System.out.println(list.indexOf("Pecan"));
        System.out.println(list.contains("Pistachio"));
        System.out.println(list.indexOf("Pistachio"));

        //  Example 2
        String[] nuts = new String[]{"Peanut", "Pecan","Cashew","Almond"};
        System.out.println(Arrays.asList(nuts).contains("Pecan"));
        System.out.println(Arrays.asList(nuts).indexOf("Pecan"));
        System.out.println(Arrays.asList(nuts).contains("Pistachio"));
        System.out.println(Arrays.asList(nuts).contains("Pistachio"));

        // Example 3 Java 8 Version
        boolean result = Arrays.stream(nuts).anyMatch(x -> x.equalsIgnoreCase("pecan"));
        System.out.println(result);

        result = Arrays.stream(nuts).anyMatch(x -> x.equalsIgnoreCase("pistachio"));
        System.out.println(result);

    }
}
