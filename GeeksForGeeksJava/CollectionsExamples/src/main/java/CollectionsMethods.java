import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsMethods {

    public static void main(String[] args) {

        // Create, Seed, Display
        List<String> list = new ArrayList<>(List.of("thingOne", "thingTwo"));
        System.out.println("Original ArrayList: " + list);


        /*
            ADDING STUFF
         */
        Collections.addAll(list, "Boy", "Girl", "Cat");
        /*
         More functional way of printing out the items.

            NOTE: for large data structures, consider using normal for or an enhanced for, as forEach performs overhead
            that makes it SLOW
         */
        list.stream().map(s -> s + " ").forEach(System.out::print);
        System.out.println();

        //

        /*
            SORTING
         */
        Collections.sort(list);

        // Using enhanced For, and removed braces.
        for (String s : list) System.out.print(s + " ");
        System.out.println();

        /*
            We can reverse sort like this:
                Collections.sort(list, Collections.reverseOrder());

                ... but the following is considered best practices.
         */
        list.sort(Collections.reverseOrder());
        for (String s : list) System.out.print(s + " ");
        System.out.println();
        List<String> oldList = list;

        /*
            SEARCHING
            - in order for binarySearch to work, the collection must be sorted in ASCENDING ORDER
         */
        list = new ArrayList<>(List.of("Gravy", "Bella", "Rosie", "Phil", "Phin", "Finn", "Zuco"));
        System.out.println("New List: " + list);

        // Wrong answer, not sorted
        System.out.println("The index of Gravy is " + Collections.binarySearch(list, "Gravy"));

        // Fixed
        Collections.sort(list);
        System.out.println("The index of Gravy is " + Collections.binarySearch(list, "Gravy"));

        //


        /*
            COPYING
            - Destination List MUST be > Source List
            - Copying overwrites on a per-index basis, which is why it fails if the Destination list is shorter
                (it results in an internal ArrayIndexOutOfBounds)

            - This also means that the indexes in the destination list beyond that of the length of the source list
            remain untouched (and still contain the previous values).
         */
        System.out.println("Source List      " + oldList);
        System.out.println("Destination List " + list);
        Collections.copy(list, oldList);
        System.out.println("Destination List " + list);


        /*
            Checking Lists for Common Elements

            DISJOINT - term used to describe two collections that have no common elements.
         */
        System.out.println("Source and Destination List are Disjoint: " + Collections.disjoint(list, oldList));
    }
}
