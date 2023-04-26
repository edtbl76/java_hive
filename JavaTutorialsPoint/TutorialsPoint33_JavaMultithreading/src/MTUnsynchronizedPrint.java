class UnsynchronizedPrint {
    void printCount() {
        try {
            for (int i = 5; i > 0; i --) {
                System.out.println("Counter   ---   " + i);
            }
        } catch (Exception e) {
            System.out.println("Thread interrupted");
        }
    }
}

class UnsynchronizedThreadDemo extends Thread {
    private Thread t;
    private String threadName;

    private UnsynchronizedPrint unsynchronizedPrint;

    UnsynchronizedThreadDemo(String name, UnsynchronizedPrint up) {
        threadName = name;
        unsynchronizedPrint = up;
    }

    public void run() {
        unsynchronizedPrint.printCount();
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Staring " + threadName);
        if (t==null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}


public class MTUnsynchronizedPrint {

    public static void main(String[] args) {
        UnsynchronizedPrint pd = new UnsynchronizedPrint();
        UnsynchronizedThreadDemo utd1 = new UnsynchronizedThreadDemo("Thread - 1", pd);
        UnsynchronizedThreadDemo utd2 = new UnsynchronizedThreadDemo("Thread - 2", pd);

        utd1.start();
        utd2.start();

        try {
            utd1.join();
            utd2.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }
    }
}
