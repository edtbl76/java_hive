package collections.arraylists;

import utils.Generated;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

@Generated
@SuppressWarnings("all")
public class BadIteratorDeletion {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(10, 20, 30, 40, 50));

        Iterator<Integer> iterator = list.iterator();

        while(iterator.hasNext()) {
            int next = iterator.next();

            try {
                if(next == 30) {
                    // Trying to remove the element directly from the list results
                    // in an exception.
                    list.remove(Integer.valueOf(30));
                }
            } catch (ConcurrentModificationException e) {
                e.printStackTrace();
            }

        }
    }
}
