package collections.arraylists;

import utils.Generated;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Generated
@SuppressWarnings("all")
public class GoodIteratorDeletion {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(10, 20, 30, 40, 50));

        Iterator<Integer> iterator = list.iterator();

        while(iterator.hasNext()) {
            int next = iterator.next();

            if (next == 30) {
                /*
                    The proper way to remove an element while iterating over it
                    is to remove it from the iterator object.
                 */
               iterator.remove();
            }

        }
        System.out.println(list);
    }
}
