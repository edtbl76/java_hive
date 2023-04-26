import java.util.HashSet;

public class HashSetClassDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        HashSet hashSet = new HashSet();

        // add elems
        hashSet.add("B");
        hashSet.add("A");
        hashSet.add("D");
        hashSet.add("E");
        hashSet.add("C");
        hashSet.add("F");
        System.out.println("Original Contents of HashSet: " + hashSet); // HashSets are ordered?
    }
}
