package CollectionsInJava.ArrayList.Methods;


import java.util.ArrayList;
import java.util.Arrays;

public class toArrayExample2 {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(2);
        list.addAll(Arrays.asList("A", "B", "C", "D"));

        // convert to string array
        String[] array = list.toArray(new String[list.size()]);
        System.out.println(Arrays.toString(array));
    }
}
