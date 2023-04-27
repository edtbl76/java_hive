package multithreading.threads.threadlocal;


import utils.Generated;

@Generated
public class DemoSafe {

    @SuppressWarnings("all")
    public static void main(String[] args) throws InterruptedException {

        ThreadLocalSafeCounter counter = new ThreadLocalSafeCounter();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            Thread thread  = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    counter.increment();
                }
                System.out.println(counter.counter.get());
            });
            threads[i] = thread;
            thread.start();
        }

        for (int i = 0; i < 100; i++) {
            threads[i].join();
        }

        System.out.println(counter.counter.get());
    }
}
