package LivenessPerformanceTesting_3.PerformanceAndScalability_11.Examples;

import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread {

    private final BlockingQueue<Runnable> queue;

    public WorkerThread(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Runnable task = queue.take();
                task.run();
            } catch (InterruptedException e) {
                /*
                    Allow thread to exit
                 */
                break;
            }
        }
    }
}
