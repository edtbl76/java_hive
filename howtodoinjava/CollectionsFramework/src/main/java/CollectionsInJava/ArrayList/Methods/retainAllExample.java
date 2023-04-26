package CollectionsInJava.ArrayList.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Opposite of Remove All. (Also requires a Collection.singleton() as an argument)
 */
public class retainAllExample {

    public static void main(String[] args) {
        ArrayList<String> alphabets = new ArrayList<>(Arrays.asList("A", "B", "A", "D", "A"));
        System.out.println(alphabets);

        alphabets.retainAll(Collections.singleton("A"));
        System.out.println(alphabets);
    }
}
