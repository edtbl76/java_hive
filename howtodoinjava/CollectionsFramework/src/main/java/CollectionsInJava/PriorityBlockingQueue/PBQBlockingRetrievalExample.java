package CollectionsInJava.PriorityBlockingQueue;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/*
    BLOCKING RETRIEVAL:
    - thread waits in a loop using the take() method
    - in the following implementation it will wait indefinitely until an element is present in the queue to consume.
 */
public class PBQBlockingRetrievalExample {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();

        new Thread(() -> {
            System.out.println("Waiting to poll!...");

            try {
                while(true) {
                    Integer poll = priorityBlockingQueue.take();
                    System.out.println("Polled: " + poll);

                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();

        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        priorityBlockingQueue.add(1);

        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        priorityBlockingQueue.add(2);

        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        priorityBlockingQueue.add(3);

    }
}
