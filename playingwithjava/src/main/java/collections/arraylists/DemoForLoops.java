package collections.arraylists;

import utils.Generated;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
@Generated
public class DemoForLoops {

    public static void main(String[] args) {

        // setup
        List<Integer> list = new ArrayList<>(List.of(10, 20, 30, 40));
        System.out.println("New List: " + list);

        // 1. Simple for loop
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

        // 2. Enhanced for loop
        for (Integer integer : list) {
            System.out.println(integer + " ");
        }
        System.out.println();

        // 3. forEach
        list.forEach(System.out::println);

    }
}
