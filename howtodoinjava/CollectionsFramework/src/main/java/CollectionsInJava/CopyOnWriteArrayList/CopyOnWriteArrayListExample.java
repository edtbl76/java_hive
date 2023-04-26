package CollectionsInJava.CopyOnWriteArrayList;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> letters = new CopyOnWriteArrayList<>(Arrays.asList("A", "B", "C", "D"));

        // No explicit synchronization call required.
        for (String letter : letters) System.out.println(letter);
    }
}
