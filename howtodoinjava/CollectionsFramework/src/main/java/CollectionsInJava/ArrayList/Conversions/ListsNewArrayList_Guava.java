package CollectionsInJava.ArrayList.Conversions;

import com.google.common.collect.Lists;

import java.util.ArrayList;

public class ListsNewArrayList_Guava {

    public static void main(String[] args) {
        String[] namesArray = new String[]{"Winken", "Blinken", "Nod"};
        ArrayList<String> namesList = Lists.newArrayList(namesArray);
        System.out.println(namesList);
    }
}
