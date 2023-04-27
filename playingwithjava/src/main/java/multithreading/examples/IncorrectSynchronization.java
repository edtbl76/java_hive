package multithreading.examples;

import utils.Generated;


import static java.lang.Boolean.TRUE;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.awaitility.Awaitility.await;

/*
    Common mistake.
    - sync on an object and then reassign that object elsewhere in the code
 */
@Generated
@SuppressWarnings("all")
public class IncorrectSynchronization {

     Boolean flag = TRUE;

    public void example() throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            synchronized (flag) {
                try {
                    while (flag) {
                        System.out.println("First thread about to sleep");
                        Thread.sleep(5000);
                        System.out.println("Woke up and about to invoke wait()");
                        flag.wait();
                    }
                } catch (InterruptedException e) {
                }
            }
        });

        Thread thread2 = new Thread(() -> {
           flag = false;
           System.out.println("Boolean assignment done.");

        });


        thread1.start();
        await().atMost(1000, MILLISECONDS);
        thread2.start();

        thread1.join();
        thread2.join();
    }

    public static void runTest() throws InterruptedException {
        IncorrectSynchronization incorrectSynchronization = new IncorrectSynchronization();
        incorrectSynchronization.example();
    }
    public static void main(String[] args) throws InterruptedException {
        IncorrectSynchronization.runTest();
    }
}
