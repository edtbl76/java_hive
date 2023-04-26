import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class UniversalIterator {

    public static void main(String[] args) {

        // This is a normal Iterator

        // Create, Seed, Show
        List<Integer> list = new ArrayList<>();
        IntStream.range(0, 10).forEach(list::add);
        System.out.println(list);

        /*
            You'll notice that removing the //noinspection line causes the IDE to squawk at you.
            It's going to suggest you loop over the list w/o creating an iterator.

                WHY?
                - Because it implements the iterator under the hood anyway for the purposes of readability.
                - Creating the iterator is more often than not just unnecessary boilerplate.

         */
        Iterator<Integer> iterator = list.iterator();

        while(iterator.hasNext()) {
            int integer = iterator.next();
            System.out.print(integer + " ");

            // Demonstrates remove.
            if (integer % 2 == 0) iterator.remove();
        }

        // Print out result.
        System.out.println("\n" + iterator);
    }
}
