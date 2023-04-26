import java.util.*;

public class CollectionsInterfaceDemo {

    @SuppressWarnings("unchecked")  // This should be replaced by Generics where plausible
    public static void main(String[] args) {

        // Arraylist
        // This is an example of Collections + List Interfaces
        List arrayList = new ArrayList();

        arrayList.add("Tinker");
        arrayList.add("Evers");
        arrayList.add("Chance");
        System.out.println("ArrayList Elements: " + arrayList);

        // LinkedList
        // This is an example of Collections + List Interfaces
        List linkedList = new LinkedList();

        linkedList.add("Geddy");
        linkedList.add("Neal");
        linkedList.add("Alex");
        System.out.println("LinkedList Elements: " + linkedList);

        //HashSet
        Set hashSet = new HashSet();

        hashSet.add("Ned");
        hashSet.add("Lucky");
        hashSet.add("Dusty");
        System.out.println("HashSet Elements: " + hashSet);

        //HashMap
        Map hashMap = new HashMap();

        hashMap.put("Guitar", "Joe");
        hashMap.put("Drums", "Jonathan");
        hashMap.put("Bass", "Stu");
        System.out.println("HashMap Elements: " + hashMap);



    }

}
