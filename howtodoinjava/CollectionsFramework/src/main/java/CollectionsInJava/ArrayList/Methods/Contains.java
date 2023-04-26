package CollectionsInJava.ArrayList.Methods;


import java.util.ArrayList;
import java.util.Arrays;

public class Contains {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("A","B","C"));
        System.out.println(list.contains("A"));
        System.out.println(list.contains("Z"));

    }
}
