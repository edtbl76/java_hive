package CollectionsInJava.ArrayList.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class listIteratorExample {

    public static void main(String[] args) throws CloneNotSupportedException {

        ArrayList<String> alphabets = new ArrayList<>(Arrays.asList("A","B","C","D"));

        ListIterator<String> iterator = alphabets.listIterator();

        System.out.println("==========FORWARD==========");

        while(iterator.hasNext())
            System.out.println(iterator.next());

        // We can add an item to the iterator.
        iterator.add("E");

        System.out.println("==========BACKWARD==========");

        while(iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }

        iterator.next();
        iterator.next();
        iterator.remove();
        System.out.println(alphabets);
    }
}
