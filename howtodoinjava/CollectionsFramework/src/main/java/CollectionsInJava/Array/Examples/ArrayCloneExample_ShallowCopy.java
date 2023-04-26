package CollectionsInJava.Array.Examples;

import java.util.stream.IntStream;

public class ArrayCloneExample_ShallowCopy {

    public static void main(String[] args) {
        Lineman[] oline = new Lineman[5];

        oline[0] = new Lineman("LT", "Duane", "Brown");
        oline[1] = new Lineman("LG", "Mike", "Iupati");
        oline[2] = new Lineman("C", "Justin", "Britt");
        oline[3] = new Lineman("RG", "D.J.", "Fluker");
        oline[4] = new Lineman("RT", "Germain", "Ifedi");

        // Print Orig
        IntStream.range(0, oline.length).forEach(value -> System.out.println(oline[value]));

        // Clone
        Lineman[] olineClone = oline.clone();

        // Make a change
        oline[2].setFirstName("Joey");
        oline[2].setLastName("Hunt");

        // Print Original
        System.out.println(oline[2]);               // Joey Hunt

        // Print Clone
        System.out.println(olineClone[2]);          // Joey Hunt

    }
}


