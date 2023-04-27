package multithreading.threads.executors;

import utils.Generated;

@Generated
public class ThreadExecutorExample {

    public static void main(String[] args) {
        SimpleExecutor executor = new SimpleExecutor();
        SimpleTask task = new SimpleTask();
        executor.execute(task);
    }
}
