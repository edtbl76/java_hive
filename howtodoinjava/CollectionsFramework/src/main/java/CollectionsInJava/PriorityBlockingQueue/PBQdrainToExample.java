package CollectionsInJava.PriorityBlockingQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.PriorityBlockingQueue;

public class PBQdrainToExample {

    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> pbq = new PriorityBlockingQueue<>(Arrays.asList(1,3,2,6,4,5));
        System.out.println("Initial Q: " + pbq);

        ArrayList<Integer> list = new ArrayList<>();
        pbq.drainTo(list, 3);
        System.out.println("Remaining: " + pbq);
        System.out.println("Drained: " + list);

        pbq.drainTo(list);
        System.out.println("Remaining: " + pbq);
        System.out.println("Drained: " + list);

    }
}
