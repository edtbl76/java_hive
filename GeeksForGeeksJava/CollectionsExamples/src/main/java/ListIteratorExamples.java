import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorExamples {

    public static void main(String[] args) {

        // Starter Drill
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0));
        System.out.println(list);


        // Create our ListIterator
        ListIterator<Integer> listIterator = list.listIterator();

        /*
            Forward!
         */
        while (listIterator.hasNext()) {

            // Instantiate element and print it
            int integer = listIterator.next();
            System.out.print(integer + " ");


            /*
                Replace Evens w/ odd and add another for good measure.
             */
             if (integer % 2 == 0) {
                integer++;
                listIterator.set(integer);
                listIterator.add(integer);
            }
        }
        System.out.println("\n" + list);
    }
}
