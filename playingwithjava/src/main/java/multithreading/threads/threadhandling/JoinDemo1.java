package multithreading.threads.threadhandling;

import utils.Generated;

@Generated
public class JoinDemo1 {

    /*
        Main creates the main application thread.
        This thread is always created.
        In single threaded applications, this is the only thread that executes.
     */
    public static void main(String[] args) {

        ExecuteMe executeMe = new ExecuteMe();
        /*
            This thread is created by the main application thread.
         */
        Thread thread = new Thread(executeMe);
        thread.setDaemon(true);
        thread.start();

        /*
            Main exits right after our thread was created.
            Since we never joined() our thread, we don't see anything.
         */
    }
}

