package com.basics.ControlStatements.While;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratingOverCollection {

    public static void main(String[] args) {

        /*
            This is another good example of a while loop. This allows us to iterate over collections without
            necessarily having to deal with the size of the collection. This is useful when the batches of work
            are going to vary.

            Typically, some work will be done on each element as it is passing through the while loop.
         */
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> iterator = list.iterator();

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
