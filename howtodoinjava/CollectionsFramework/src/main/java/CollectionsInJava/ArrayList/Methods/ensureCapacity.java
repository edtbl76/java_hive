package CollectionsInJava.ArrayList.Methods;

import java.util.ArrayList;
import java.util.Arrays;

public class ensureCapacity {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(2);
        list.addAll(Arrays.asList("A", "B"));
        System.out.println(list);

        list.ensureCapacity(20);
        list.addAll(Arrays.asList("C","D"));
        System.out.println(list);
    }
}
