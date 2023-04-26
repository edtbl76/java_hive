package CollectionsInJava.ArrayList.Methods;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class clear {

    public static void main(String[] args) {
        // Example
        ArrayList<Integer> arrayList = new ArrayList<>();
        IntStream.range(0, 10).forEach(arrayList::add);
        System.out.println(arrayList);

        // cleared
        arrayList.clear();
        System.out.println(arrayList);

        /*
            NOTE:
                - An EMPTY ArrayList has zero elements
                - a NEW ArrayList also has zero elements.

                However, there is a BACKING ARRAY (internals) to the ArrayList.
                - when clear() is called, the BackingArray isn't resized.
         */
    }
}
