package CollectionsInJava.LinkedTransferQueue;

import java.util.concurrent.LinkedTransferQueue;

public class LTQExample {
    public static void main(String[] args) {
        LinkedTransferQueue<Integer> ltq = new LinkedTransferQueue<>();
        ltq.put(1);
        System.out.println("Added Message = 1");

        Integer message = ltq.poll();
        System.out.println("RCVD Message = " + message);

    }
}
