package CollectionsInJava.ArrayList.Examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SynchronizedArrayList {

    public static void main(String[] args) {
        List<String> names = Collections.synchronizedList(new ArrayList<>());

        names.add("Kyle");
        names.add("Cartman");
        names.add("Stan");
        names.add("Kenny");

        System.out.println(names);

        synchronized (names) {
            Iterator<String> iterator = names.iterator();
            while (iterator.hasNext())
                System.out.println(iterator.next());

        }
    }
}
