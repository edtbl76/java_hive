package CollectionsInJava.ArrayList.Examples;

import java.util.ArrayList;
import java.util.Arrays;

public class AddReplaceElementExamples {

    public static void main(String[] args) {

        // initial.
        ArrayList<String> names = new ArrayList<>(Arrays.asList("Ed", "Vanessa", "Michael", "Connor"));
        System.out.println(names);

        // add somebody
        names.add("Bowie");
        System.out.println(names);

        // replace
        names.set(names.size() - 1, "Bogie");
        System.out.println(names);

        // replace during iteration
        names.forEach(name -> {
            if(name.equalsIgnoreCase("Bogie")) {
                names.set(names.indexOf("Bogie"), "Clark");
            }
        });
    }
}
