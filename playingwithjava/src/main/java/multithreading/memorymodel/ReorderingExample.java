package multithreading.memorymodel;

import utils.Generated;

@Generated
@SuppressWarnings("all")
public class ReorderingExample {

    // shared data
    int sharedA = 0;
    int sharedB = 0;

    // executions
    void method1() {
        int localA;
        localA = sharedA;
        sharedB = 1;
        System.out.println("Local A = " + localA);
    }

    void method2() {
        int localB;
        localB = sharedB;
        sharedA = 2;
        System.out.println("Local B = " + localB);
    }

    public static void main(String[] args) throws InterruptedException {
        final ReorderingExample reorderingExample = new ReorderingExample();

        Thread thread1 = new Thread(reorderingExample::method1);
        Thread thread2 = new Thread(reorderingExample::method2);

        /*
            Since I haven't guarded any of these variables, and how the thread access them,
            the JVM can honor the 'within-thread as-if-serial' semantics, and move
            all of this shit around as it pleases.
            However... that means that the visibility of the data isn't consistent.
         */
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
