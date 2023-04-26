package CollectionsInJava.ArrayList.Methods;

import java.util.ArrayList;
import java.util.Arrays;

public class Cloning {

    public static void main(String[] args) {
        ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C"));
        System.out.println(alphabet);
        ArrayList<String> alphaClone = (ArrayList<String>)alphabet.clone();
        System.out.println(alphaClone);
    }
}
