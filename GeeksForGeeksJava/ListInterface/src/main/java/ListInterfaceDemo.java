import java.util.ArrayList;
import java.util.List;

public class ListInterfaceDemo {

    public static void main(String[] args) {

        // Create a list
        List<Integer> listOne = new ArrayList<>();

        // Adding elements at index
        listOne.add(0, 1);
        listOne.add(1, 2);

        // adding elements at end of list
        listOne.add(3);
        listOne.add(4);
        listOne.add(5);

        System.out.println("Added: " + listOne);

        /*
            Update elements w/ set
         */
        listOne.set(0, 0);
        listOne.set(2, 0);
        listOne.set(4, 0);
        System.out.println("Updated: " + listOne);

        /*
            Removing Objects
         */
        // remove first occurence
        listOne.remove(Integer.valueOf(0));
        // remove by index
        listOne.remove(3);
        System.out.println("Removed: " + listOne);


        /*
            Iterating
         */

        // for loop + get()
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < listOne.size(); i++) {
            System.out.print(listOne.get(i) + " ");
        }
        System.out.println();

        // enhanced for
        for (Integer i : listOne){
            System.out.print(i + " ");
        }
        System.out.println();

    }
}
