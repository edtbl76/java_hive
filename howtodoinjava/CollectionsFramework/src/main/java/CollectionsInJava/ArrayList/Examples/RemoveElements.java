package CollectionsInJava.ArrayList.Examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class RemoveElements {

    public static void main(String[] args) {
        ArrayList<String> stooges = new ArrayList<>(Arrays.asList("Larry", "Moe", "Curly", "Shep"));
        System.out.println(stooges);

        // Remove by index
        stooges.remove(stooges.size() - 1);
        System.out.println(stooges);

        // Remove by value
        stooges.remove("Larry");
        System.out.println(stooges);

        // RemoveIf
        Predicate<String> condition = name -> name.equals("Moe");
        stooges.removeIf(condition);
        System.out.println(stooges);


    }

}
