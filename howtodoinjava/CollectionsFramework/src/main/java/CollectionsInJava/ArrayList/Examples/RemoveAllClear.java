package CollectionsInJava.ArrayList.Examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveAllClear {

    public static void main(String[] args) {
        List<String> letters = Arrays.asList("A","B","C","D");

        ArrayList<String> list = new ArrayList<>(letters);
        System.out.println(list);

        // clear()
        /*
            Better performance because this doesn't check if an index has an element. It just iterates through and sets
            everything to null
         */
        list.clear();
        System.out.println(list);

        // (add 'em back)
        list.addAll(letters);
        System.out.println(list);

        // removeAll()
        /*
            This is MUUUUCH slower, because it ensures that an element has something before it removes the element.
            It has better uses.
         */
        list.removeAll(list);
        System.out.println(list);

    }
}
