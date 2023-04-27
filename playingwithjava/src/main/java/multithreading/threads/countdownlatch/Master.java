package multithreading.threads.countdownlatch;

import utils.Generated;

@SuppressWarnings("all")
@Generated
public class Master extends Thread {

    public Master(String name) {
        super(name);
    }

    @Override
    public void run() {

        System.out.println("Master executed " + Thread.currentThread().getName());


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
