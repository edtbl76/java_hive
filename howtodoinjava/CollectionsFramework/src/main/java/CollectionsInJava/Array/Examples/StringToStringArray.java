package CollectionsInJava.Array.Examples;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringToStringArray {

    public static void main(String[] args) {

        // String split()
        String names = "Tom,Dick,Harry";
        String[] namesArray = names.split(",");
        System.out.println(Arrays.toString(namesArray));

        // Pattern.compile()
        Pattern pattern = Pattern.compile(",");
        namesArray = pattern.split(names);
        System.out.println(Arrays.toString(namesArray));

        // String.join()
        System.out.println(String.join("", namesArray));
        System.out.println(String.join(" ", namesArray));
        System.out.println(String.join("-", namesArray));

    }
}
