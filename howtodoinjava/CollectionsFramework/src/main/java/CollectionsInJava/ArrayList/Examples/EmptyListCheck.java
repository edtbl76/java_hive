package CollectionsInJava.ArrayList.Examples;

import java.util.ArrayList;
import java.util.List;

public class EmptyListCheck {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        printer(list);

        list.add("Something");
        printer(list);

        list.clear();
        printer(list);

    }

    private static void printer(List<String> list) {
        System.out.println("=====================");
        System.out.println("Size   : " + list.size());
        System.out.println("isEmpty: " + list.isEmpty());
    }
}
