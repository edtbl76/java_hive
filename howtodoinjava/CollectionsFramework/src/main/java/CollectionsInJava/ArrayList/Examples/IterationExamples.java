package CollectionsInJava.ArrayList.Examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class IterationExamples {

    public static void main(String[] args) {
        ArrayList<String> family = new ArrayList<>(Arrays.asList("Ed", "Vanessa", "Connor", "Michael"));

        // 1 old fashioned for loop
        for (int i = 0; i < family.size(); i++)
            System.out.println(family.get(i));

        // 2 enhanced for loop
        for (String f : family)
            System.out.println(f);

        // 3 List iterator
        ListIterator<String> iterator = family.listIterator();
        while(iterator.hasNext())
            System.out.println(iterator.next());

        // 4 while loop
        int index = 0;
        while (family.size() > index)
            System.out.println(family.get(index++));

        // 5 Java 8 Stream (Lambda)
        family.forEach(name -> System.out.println(name));

        // 6 Java 8 Stream (Method Reference)
        family.forEach(System.out::println);

    }


}
