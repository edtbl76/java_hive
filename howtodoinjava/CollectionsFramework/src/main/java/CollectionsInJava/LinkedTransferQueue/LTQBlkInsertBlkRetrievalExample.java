package CollectionsInJava.LinkedTransferQueue;

import java.util.Random;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class LTQBlkInsertBlkRetrievalExample {
    /*
        PRODUCER THREAD
            - waits until there is a consumer ready to take() the item from the queue
            - will only send another message AFTER the consumer has taken the produced message.

        CONSUMER THREAD
            - waits if queue is empty
            - as soon as there is a single element in queue, it will take() it out.
     */
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<Integer> ltq = new LinkedTransferQueue<>();

        // PRODUCER
        new Thread(() -> {
            Random random = new Random(1);
            try {
                while(true) {
                    System.out.println("Producer is waiting to transfer message...");

                    Integer message = random.nextInt();
                    if (ltq.tryTransfer(message)) {
                        System.out.println("Producer added the message - " + message);
                    }
                    Thread.sleep(TimeUnit.SECONDS.toMillis(3));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();

        // CONSUMER
        new Thread(() -> {
            try {
                while(true) {
                    System.out.println("Consumer is waiting to take message...");

                    Integer message = ltq.take();
                    System.out.println("Consumer RCVD message - " + message);
                    Thread.sleep(TimeUnit.SECONDS.toMillis(3));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}
