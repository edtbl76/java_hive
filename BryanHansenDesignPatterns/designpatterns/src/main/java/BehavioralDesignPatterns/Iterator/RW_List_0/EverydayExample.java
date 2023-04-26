package BehavioralDesignPatterns.Iterator.RW_List_0;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EverydayExample {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("Ed");
        names.add("Paul");
        names.add("Anthony");

        Iterator<String> iterator = names.iterator();

        while(iterator.hasNext()){
            String name = iterator.next();
            System.out.println(name);
            iterator.remove();
        }

        System.out.println("Names Left: " + names.size());
    }
}
