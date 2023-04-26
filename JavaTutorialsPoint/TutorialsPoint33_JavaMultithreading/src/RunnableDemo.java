import java.util.stream.IntStream;

class RunnableDemoTest implements Runnable {

    private Thread t;
    private String threadName;

    RunnableDemoTest(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    public void run() {
        System.out.println("Running " + threadName);

        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName  + " interrupted.");
        }
        System.out.println("Thread : " + threadName + " exiting.");
    }

    void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread (this, threadName);
            t.start();
        }
    }
}

public class RunnableDemo {

    public static void main(String[] args) {
        RunnableDemoTest runnableDemoTest1 = new RunnableDemoTest("Thread-1");
        runnableDemoTest1.start();

        RunnableDemoTest runnableDemoTest2 = new RunnableDemoTest("Thread-2");
        runnableDemoTest2.start();

    }
}
