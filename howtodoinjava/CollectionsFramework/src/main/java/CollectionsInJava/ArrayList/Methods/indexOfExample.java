package CollectionsInJava.ArrayList.Methods;

import java.util.ArrayList;
import java.util.Arrays;

public class indexOfExample {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "A"));
        System.out.println(list.indexOf("A"));
        System.out.println(list.lastIndexOf("A"));
        System.out.println(list.indexOf("No"));

    }
}
