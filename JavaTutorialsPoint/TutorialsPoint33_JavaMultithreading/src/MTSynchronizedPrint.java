class SynchronizedPrint {
    void printCount() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Counter   ---   " + i);
            }
        } catch (Exception e) {
            System.out.println("Thread interrupted.");
        }
    }
}

class SynchronizedThread extends Thread {
    private Thread t;
    private final String threadName;

    private final SynchronizedPrint synchronizedPrint;

    SynchronizedThread(String name, SynchronizedPrint sp) {
        threadName = name;
        synchronizedPrint = sp;
    }

    public void run() {
        // SYNCHRONIZED BLOCK!!!!!!!!!
        synchronized (synchronizedPrint) {
            // the resources in this block are shared by both threads.
            synchronizedPrint.printCount();
        }
        System.out.println("Thread " + threadName + " exiting");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}

public class MTSynchronizedPrint {

    public static void main(String[] args) {
        SynchronizedPrint sp = new SynchronizedPrint();

        SynchronizedThread st1 = new SynchronizedThread("Thread - 1", sp);
        SynchronizedThread st2 = new SynchronizedThread("Thread - 2", sp);

        st1.start();
        st2.start();

        // wait for threads
        try {
            st1.join();
            st2.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }
    }
}
