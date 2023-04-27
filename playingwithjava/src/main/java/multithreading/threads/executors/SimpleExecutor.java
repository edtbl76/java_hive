package multithreading.threads.executors;

import utils.Generated;

import java.util.concurrent.Executor;

@Generated
@SuppressWarnings("all")
public class SimpleExecutor  implements Executor {
    @Override
    public void execute(Runnable command) {
        Thread thread = new Thread(command);
        thread.start();
    }
}
