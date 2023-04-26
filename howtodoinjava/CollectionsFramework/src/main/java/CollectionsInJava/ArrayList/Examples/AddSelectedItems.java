package CollectionsInJava.ArrayList.Examples;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddSelectedItems {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Ed", "Vanessa", "Michael", "Connor");
        ArrayList<String> kids = new ArrayList<>();
        ArrayList<String> parents = new ArrayList<>();

        list.stream()
                .filter(name -> !name.equalsIgnoreCase("Ed") && !name.equalsIgnoreCase("Vanessa"))
                .forEachOrdered(kids::add);

        list.stream()
                .filter(name -> !name.equalsIgnoreCase("michael") && !name.equalsIgnoreCase("connor"))
                .forEachOrdered(parents::add);

        System.out.println(kids);
        System.out.println(parents);
    }
}
