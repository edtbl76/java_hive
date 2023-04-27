package multithreading.threads.completions;

import utils.Generated;

import java.util.Random;
import java.util.concurrent.*;

import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.Executors.*;

/*
    Completion Service is a combination of
    - BlockingQueue
    - Executor

    - This makes it easier to handle thousands of task submissions.

 */
@SuppressWarnings("all")
@Generated
public class DemoCompletionService {



    static void example() throws ExecutionException, InterruptedException {


        ExecutorService threadPool = newFixedThreadPool(3);
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(threadPool);

        // 10 trivial tasks
        for (int i = 0; i < 10; i++) {
            completionService.submit(new TrivialTask(i), i);
        }

        // wait for completion
        int count = 10;
        while (count != 0) {
            Future<Integer> future = completionService.poll();
            if (future != null) {
                System.out.println("Thread" + future.get() + " completed");
                count --;
            }
        }

        threadPool.shutdown();

    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DemoCompletionService.example();
    }

}

@SuppressWarnings("all")
@Generated
class TrivialTask implements Runnable {

    int number;

    static Random random = new Random(currentTimeMillis());

    public TrivialTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(random.nextInt(101));
        } catch (InterruptedException ignored) {
            // swallow it all
        }

        System.out.println(this.number * this.number);
    }
}