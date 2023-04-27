package multithreading.threads.executors;

import utils.Generated;

import java.util.concurrent.Executor;

import static java.util.concurrent.Executors.*;

@Generated
@SuppressWarnings("all")
public class ExecutorImplementations {

    Order waitForNextOrder() {
        return new Order();
    }

    // Example 1 - Sequential, no concurrency support
    void receiveAndExecuteClientOrders() {
        Order order = waitForNextOrder();
        order.execute();
    }


    // Example 2 - slightly better, but doesn't control number of threads created
    void receiveAndExecuteClientOrders2() {
        final Order order2 = waitForNextOrder();

        Thread thread = new Thread(order2::execute);
        thread.start();
    }

    // Example 3 - Best -> Use Thread Pools
    void receiveAndExecuteClientOrders3 () {
        int maxConcurrentOrders = 100;
        Executor executor = newFixedThreadPool(maxConcurrentOrders);

        while (true) {
            final Order order = waitForNextOrder();
            executor.execute(order::execute);
        }
    }

}

@Generated
@SuppressWarnings("all")
class Order implements Executor {


    public void execute() {
        Thread thread = new Thread(() -> {
            // do something.
        });
        thread.start();
    }

    @Override
    public void execute(Runnable command) {
        // do something
    }
}