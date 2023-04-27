package multithreading.examples;

import utils.Generated;

@Generated
@SuppressWarnings("all")
public class DemoVolatile {

    /*
        volatile on its own doesn't provide thread safety. If multiple threads can write
        to this variable, then we need `synchronized`

        this just forces reads to happen in main memory, avoiding the pitfalls of caching
        and concurrency.
     */
    static volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {

        int threadCount = 10;
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    count++;
                }
            });
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        System.out.println("count = " + threadCount);
    }

}
