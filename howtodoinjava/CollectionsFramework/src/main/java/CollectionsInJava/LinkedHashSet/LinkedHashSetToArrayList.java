package CollectionsInJava.LinkedHashSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LinkedHashSetToArrayList {

    public static void main(String[] args) {
        LinkedHashSet<String> set = new LinkedHashSet<>(Arrays.asList("a", "b", "c", "d", "e"));
        System.out.println(set);

        List<String> values = new ArrayList<>(set);
        System.out.println(values);
    }
}
