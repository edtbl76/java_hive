package collections.arraylists;

import utils.Generated;

import java.util.ArrayList;
import java.util.List;

@Generated
@SuppressWarnings("all")
public class AvoidRawCollections {

    public static void noNo(List list) {
        list.add("something");
    }
    public static void main(String[] args) {
        List list = new ArrayList(List.of(10, 20));
        noNo(list);

        /*
            This throws a class cast exception, because the method above
            is adding a string to a list that is already carrying Integers.


         */
        try {
            Integer integer = (Integer) list.get(2);
        } catch (ClassCastException cce) {
            cce.printStackTrace();
        }
    }
}
