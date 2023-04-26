import java.util.ArrayList;

public class ArrayListDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        ArrayList arrayList = new ArrayList<>();

        // add stuff
        arrayList.add("C");
        arrayList.add("A");
        arrayList.add("E");
        arrayList.add("B");
        arrayList.add("D");
        arrayList.add("F");
        arrayList.add(1, "A2");

        System.out.println("Size of al: " + arrayList.size());
        System.out.println("Original contents of al: " + arrayList);

        // Remove some elements
        arrayList.remove("F"); //remove by value
        arrayList.remove(2); //remove by index

        System.out.println("Size after remove: " + arrayList.size());
        System.out.println("Contents after remove: " + arrayList);


    }
}
