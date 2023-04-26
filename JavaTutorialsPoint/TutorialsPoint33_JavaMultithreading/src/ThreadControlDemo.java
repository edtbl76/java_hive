class RunDemo implements Runnable {
    Thread t;
    private String threadName;
    private boolean suspended = false;

    RunDemo(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    public void run() {
        System.out.println("Running " + threadName);
        try {
            for(int i = 10; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);

                // take a nap
                Thread.sleep(300);
                synchronized (this) {
                    while(suspended) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted");
        }
        System.out.println("Thread " + threadName + " exiting");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null){
            t = new Thread(this, threadName);
            t.start();
        }
    }

    void suspend() {
        suspended = true;
    }

    synchronized void resume() {
        suspended = false;
        notify();
    }
}

public class ThreadControlDemo {

    public static void main(String[] args) {
        RunDemo runDemo1 = new RunDemo("Thread-1");
        runDemo1.start();

        RunDemo runDemo2 = new RunDemo("Thread-2");
        runDemo2.start();

        try {
            Thread.sleep(1000);
            runDemo1.suspend();
            System.out.println("Suspending First Thread");
            Thread.sleep(1000);
            runDemo1.resume();
            System.out.println("Resuming First Thread");

            runDemo2.suspend();
            System.out.println("Suspending Second Thread");
            Thread.sleep(1000);
            runDemo2.resume();
            System.out.println("Resuming Second Thread");
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        } try {
            System.out.println("Waiting for threads to finish.");
            runDemo1.t.join();
            runDemo2.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted.");
        }
        System.out.println("Main thread exiting.");

    }
}
