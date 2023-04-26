package CollectionsInJava.ArrayList.Methods;

import java.util.ArrayList;
import java.util.Arrays;

public class toArrayExample {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(2);
        list.addAll(Arrays.asList("A", "B", "C", "D"));

        System.out.println(list);

        // Convert to Object Array, then print as a string.
        Object[] array = list.toArray();
        System.out.println(Arrays.toString(array));

        // Iterator and convert to whatever type you want
        for (Object o : array) {
            String s = (String)o;
            System.out.println(s);
        }
    }
}
