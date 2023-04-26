package CollectionsInJava.ArrayList.Examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergingLists_java8_flatMap {

    public static void main(String[] args) {
        ArrayList<String> ray = new ArrayList<>(Arrays.asList("Don't", "cross"));
        ArrayList<String> venkman = new ArrayList<>(Arrays.asList("the", "streams"));

        List<String> ghostbusters = Stream.of(ray, venkman)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(ray + "\n" + venkman);
        System.out.println(ghostbusters);
    }
}
