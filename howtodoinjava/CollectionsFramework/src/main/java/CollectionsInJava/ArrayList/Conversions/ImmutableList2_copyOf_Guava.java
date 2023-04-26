package CollectionsInJava.ArrayList.Conversions;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class ImmutableList2_copyOf_Guava {

    public static void main(String[] args) {
        String[] namesArray = new String[]{"Winken", "Blinken", "Nod"};
        List<String> namesList = ImmutableList.copyOf(namesArray);
        System.out.println(namesList);
    }
}
