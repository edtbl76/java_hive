package CollectionsInJava.ArrayList.Methods;

import java.util.ArrayList;
import java.util.Arrays;

public class get {

    public static void main(String[] args) {
        ArrayList<String> name = new ArrayList<>(Arrays.asList("Edward", "Mangini"));

        String firstName = name.get(0);
        String lastName = name.get(1);

        System.out.println(lastName + ", " + firstName);
    }
}
