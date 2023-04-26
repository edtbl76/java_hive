import java.util.LinkedHashSet;

public class LinkedHashSetClassDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        LinkedHashSet linkedHashSet = new LinkedHashSet();

        // add elems
        linkedHashSet.add("B");
        linkedHashSet.add("A");
        linkedHashSet.add("D");
        linkedHashSet.add("E");
        linkedHashSet.add("C");
        linkedHashSet.add("F");
        // The output here is going to retain the insertion order (that's the major difference between HashSet
        // and LinkedHashSet
        System.out.println("Original Contents of LinkedHashSet: " + linkedHashSet);
    }
}
