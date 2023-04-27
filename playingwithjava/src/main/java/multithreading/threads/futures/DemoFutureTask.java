package multithreading.threads.futures;

import utils.Generated;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.*;

@Generated
@SuppressWarnings("all")
public class DemoFutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ignored) {
                // swallow it
            }
            return 5;
        });

        ExecutorService threadPool = newSingleThreadExecutor();
        Future<Integer> duplicateFuture = (Future<Integer>) threadPool.submit(futureTask);

        while (!futureTask.isDone()) {
            System.out.println("Waiting");
        }

        if (duplicateFuture.isDone() != futureTask.isDone()) {
            System.out.println("This should never happen");
        }

        System.out.println(futureTask.get());
        threadPool.shutdown();
    }




}
