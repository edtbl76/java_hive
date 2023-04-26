package CollectionsInJava.ArrayList.Conversions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImmutableList {

    public static void main(String[] args) {
        String[] namesArray = new String[]{"Winken", "Blinken", "Nod"};

        List<String> namesList = Collections.unmodifiableList(Arrays.asList(namesArray));
        System.out.println(namesList);
    }
}
