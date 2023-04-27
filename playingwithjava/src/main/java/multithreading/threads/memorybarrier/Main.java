package multithreading.threads.memorybarrier;

import utils.Generated;

import java.util.concurrent.CyclicBarrier;

@Generated
@SuppressWarnings("java:S106")
public class Main {

    public static void main(String[] args) {
        final CyclicBarrier barrier =
                new CyclicBarrier(3, () -> System.out.println("All parties have arrived at the barrier. Continue Execution"));

        Thread thread1 = new Thread(new CBTask(barrier), "Thread 1");
        Thread thread2 = new Thread(new CBTask(barrier), "Thread 2");
        Thread thread3 = new Thread(new CBTask(barrier), "Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();

    }

}
