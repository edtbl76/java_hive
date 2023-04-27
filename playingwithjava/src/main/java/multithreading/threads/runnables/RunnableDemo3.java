package multithreading.threads.runnables;


import utils.Generated;

/*
    Similar to RunnabelDemo2.
    - instead of implementing Runnable, we subclass thread.

    CON:
    - this limits code flexibility, because it is forced to subclass thread.
    - option 2 is nicer.
 */

@Generated
public class RunnableDemo3 {

    public static void main(String[] args) throws InterruptedException {
        ExecuteMeSub executeMeSub = new ExecuteMeSub();
        executeMeSub.start();
        executeMeSub.join();
    }
}

@Generated
@SuppressWarnings("all")
class ExecuteMeSub extends Thread {

    @Override
    public void run() {
        System.out.println("Hello, thread");
    }
}
