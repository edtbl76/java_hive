package CollectionsInJava.Array.Examples;

import java.util.Arrays;

public class ArrayCopyExample {

    /*
        These are SHALLOW COPIES
     */
    public static void main(String[] args) {
        String[] names = {"War", "Pestilence", "Death", "Famine"};
        System.out.println(Arrays.toString(names));

        // clone
        String[] namesClone = names.clone();
        System.out.println(Arrays.toString(namesClone));

        //copy of
        String[] copyOfNames = Arrays.copyOf(names,names.length);
        System.out.println(Arrays.toString(copyOfNames));

        // Often recommended, but this is HORRIBLE to read.
        String[] arraycopyNames = new String[names.length];
        System.arraycopy(names, 0, arraycopyNames, 0, arraycopyNames.length );
        System.out.println(Arrays.toString(arraycopyNames));

    }
}
