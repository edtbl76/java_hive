package CollectionsInJava.ArrayList.Examples;

import java.util.ArrayList;
import java.util.Arrays;

public class MergingLists {

    public static void main(String[] args) {
        ArrayList<String> kids = new ArrayList<>(Arrays.asList("Mike", "Connor"));
        ArrayList<String> parents = new ArrayList<>(Arrays.asList("Ed", "Vanessa"));
        System.out.println(kids + "\n" + parents);

        // merge via addAll()
        kids.addAll(parents);
        System.out.println(kids);
    }
}
