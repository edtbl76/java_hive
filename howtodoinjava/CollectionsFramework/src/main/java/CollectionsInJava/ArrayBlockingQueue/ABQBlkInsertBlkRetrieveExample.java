package CollectionsInJava.ArrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ABQBlkInsertBlkRetrieveExample {

    /*
        PRODUCER THREAD waits when Q is full
            - as soon as element is taken from Q, it ADDS an element to Q

        CONSUMER THREAD waits when Q is empty
            - as soon as single element is in Q, it takes it out.

     */
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> pbq = new ArrayBlockingQueue<>(5);

        // Producer
        new Thread(() -> {
            int i = 0;
            try {
                while (true) {
                    pbq.put(++i);
                    System.out.println("Added: " + i);
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();

        // Consumer
        new Thread(() -> {
            try {
                while (true) {
                    Integer poll = pbq.take();
                    System.out.println("Polled: " + poll);
                    Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}
