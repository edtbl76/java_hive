public class DeadLockDemo {

    private static final Object Lock1 = new Object();
    private static final Object Lock2 = new Object();

    /*
    NOTE: This is going to hang forever.

        Neither of the threads are in a position to proceed,
        so they are waiting for each other to release the lock.
     */
    public static void main(String[] args) {
        DLThread1 T1 = new DLThread1();
        DLThread2 T2 = new DLThread2();
        T1.start();
        T2.start();
    }

    private static class DLThread1 extends Thread {
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread 1: Holding Lock1...");

                try { Thread.sleep(10); }
                catch (InterruptedException ignored) {}
                System.out.println("Thread 1: Waiting for Lock2...");

                synchronized (Lock2) {
                    System.out.println("Thread 1: Holding Lock1 and Lock2...");
                }
            }
        }
    }

    private static class DLThread2 extends Thread {
        public void run() {
            synchronized (Lock2) {
                System.out.println("Thread 2: Holding Lock2...");

                try { Thread.sleep(10); }
                catch (InterruptedException ignored) {}
                System.out.println("Thread 2: Waiting for Lock1...");

                synchronized (Lock1){
                    System.out.println("Thread2: Holding Lock1 and Lock2...");
                }
            }
        }
    }
}
