import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Iterator;

public class SortedSetInterfaceDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        // Create SortedSet
        SortedSet set = new TreeSet();

        // add elements
        set.add("Wonder Woman");
        set.add("Batman");
        set.add("Superman");

        // create iterator

        for (Object o : set) {
            // print elements
            System.out.println(o.toString());
        }
    }
}
