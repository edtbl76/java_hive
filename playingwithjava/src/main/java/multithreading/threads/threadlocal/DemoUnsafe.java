package multithreading.threads.threadlocal;


import utils.Generated;

@Generated
public class DemoUnsafe {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) throws InterruptedException {

        UnsafeCounter  counter = new UnsafeCounter();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            Thread thread  = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    counter.increment();
                }
            });
            threads[i] = thread;
            thread.start();
        }

        for (int i = 0; i < 100; i++) {
            threads[i].join();
        }

        System.out.println(counter.count);
    }
}
