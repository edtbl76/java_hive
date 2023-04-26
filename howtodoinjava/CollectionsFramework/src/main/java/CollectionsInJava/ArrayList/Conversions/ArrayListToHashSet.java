package CollectionsInJava.ArrayList.Conversions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class ArrayListToHashSet {

    public static void main(String[] args) {
        // The most common  reason to do this is to remove duplicate elements from a list.
        ArrayList<String> namesList = new ArrayList<>(Arrays.asList("Nod", "Winken", "Nod", "Blinken", "Nod"));
        System.out.println(namesList);

        HashSet<String> namesSet = new HashSet<>(namesList);
        System.out.println(namesSet);
    }
}
