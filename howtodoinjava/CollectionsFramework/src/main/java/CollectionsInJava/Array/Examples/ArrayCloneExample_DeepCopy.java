package CollectionsInJava.Array.Examples;

import java.util.stream.IntStream;
import org.apache.commons.lang3.SerializationUtils;

public class ArrayCloneExample_DeepCopy {
    public static void main(String[] args) {
        Lineman[] oline = new Lineman[5];

        oline[0] = new Lineman("LT", "Duane", "Brown");
        oline[1] = new Lineman("LG", "Mike", "Iupati");
        oline[2] = new Lineman("C", "Justin", "Britt");
        oline[3] = new Lineman("RG", "D.J.", "Fluker");
        oline[4] = new Lineman("RT", "Germain", "Ifedi");

        // Print Orig
        IntStream.range(0, oline.length).forEach(value -> System.out.println(oline[value]));

        // Deep Copied Array
        Lineman[] olineDC = SerializationUtils.clone(oline);

        // Make a change.
        oline[2].setFirstName("Joey");
        oline[2].setLastName("Hunt");

        // Verify change in original
        System.out.println(oline[2]);

        // Verify change in deep copied array.. UNCHANGED!
        System.out.println(olineDC[2]);
    }

}
