package CollectionsInJava.ArrayList.Conversions;

import java.util.Arrays;
import java.util.List;

public class ArraysAsList_Mutable {

    public static void main(String[] args) {
        String[] namesArray = new String[]{"Winken", "Blinken", "Nod"};
        List<String> namesList = Arrays.asList(namesArray);
        System.out.println(namesList);
    }
}
