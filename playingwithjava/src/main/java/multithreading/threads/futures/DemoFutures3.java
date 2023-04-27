package multithreading.threads.futures;

import utils.Generated;
import utils.Utils;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.*;

@Generated
@SuppressWarnings("all")
public class DemoFutures3 {

    static ExecutorService threadPool = newSingleThreadExecutor();

    public static void main(String[] args) {
        System.out.println(pollingStatusAndTaskCancellation(10));
        threadPool.shutdown();
    }

    static int pollingStatusAndTaskCancellation(final int number) {

        int result = -1;
        // Set up Callable Work
        Callable<Integer> sumTask = () -> summation(number);
        Callable<Void> randomTask = DemoFutures3::doSomething;

        // Set up Futures for thread's task submission
        Future<Integer> sumFuture = threadPool.submit(sumTask);
        Future<Void> randomFuture = threadPool.submit(randomTask);

        // Poll for completion of first task.
        try {

            /*
                Cancel second task before polling for completion of first
             */
            randomFuture.cancel(true);

            /*
                Polling future to check status of first submitted task.
                - Remember... always in while loops.
             */
            while (!sumFuture.isDone()) {
                System.out.println("Waiting for first task to complete.");
            }
            result = sumFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Something went wrong.");
        }

        System.out.println("\nis Second task cancelled: " + randomFuture.isCancelled());

        return result;
    }

    public static int summation(int number) throws InterruptedException {
        Thread.sleep(10);

        return Utils.summation(number);
    }

    public static Void doSomething() throws InterruptedException {
        Thread.sleep(3_600_000);
        return null;
    }
}
