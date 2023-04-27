package multithreading.threads.threadhandling;

import utils.Generated;

/*
- This is the proper implementation w/ join() so that we
see the output of the thread.
 */
@Generated
public class JoinDemo2 {

    public static void main(String[] args) throws InterruptedException {
        ExecuteMe executeMe = new ExecuteMe();
        Thread thread = new Thread(executeMe);

        /*
            Daemon threads run in the bg.
            - They are killed as soon as the main application thread exits.

            NOTE: If a thread isn't marked as a daemon thread, then JVMs will wait
            for the thread to finish its work before tearing it down.

            This can cause applications to hang on shut down if the spawned thread is
            stuck.
         */
        thread.setDaemon(true);
        thread.start();
        thread.join();
    }
}
