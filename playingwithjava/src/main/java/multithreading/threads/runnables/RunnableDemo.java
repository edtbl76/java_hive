package multithreading.threads.runnables;

import utils.Generated;

@Generated
@SuppressWarnings("all")
public class RunnableDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {

            @Generated
            @Override
            public void run() {
                System.out.println("Hello Thread!");
            }
        });
        thread.start();
    }
}
