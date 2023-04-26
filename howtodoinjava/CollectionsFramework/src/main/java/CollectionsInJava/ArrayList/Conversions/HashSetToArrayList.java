package CollectionsInJava.ArrayList.Conversions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class HashSetToArrayList {

    public static void main(String[] args) {
        HashSet<String> namesSet = new HashSet<>(Arrays.asList("Winken", "Blinken", "Nod"));
        ArrayList<String> namesList = new ArrayList<>(namesSet);
        System.out.println(namesList);

        // oneliner
        System.out.println(new ArrayList<>(namesSet));
    }
}
