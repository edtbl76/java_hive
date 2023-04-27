package multithreading.threads.memorybarrier;

import utils.Generated;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Generated
@SuppressWarnings("all")
public class CBTask implements Runnable {

    private CyclicBarrier barrier;

    public CBTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " has crossed barrier");

        } catch (BrokenBarrierException e) {
            // do nothing
        } catch (InterruptedException e) {
            // do nothing
        }
    }
}
