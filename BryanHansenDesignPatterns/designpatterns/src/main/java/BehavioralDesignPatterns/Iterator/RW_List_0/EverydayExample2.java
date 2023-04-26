package BehavioralDesignPatterns.Iterator.RW_List_0;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EverydayExample2 {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("Ed");
        names.add("Paul");
        names.add("Anthony");

        // foreach uses Iterator
        names.forEach(System.out::println);


        System.out.println("Names Left: " + names.size());
    }
}
