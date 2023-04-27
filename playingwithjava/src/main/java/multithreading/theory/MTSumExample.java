package multithreading.theory;

import utils.Generated;

import static java.lang.Integer.*;
import static java.lang.System.currentTimeMillis;

@Generated
public class MTSumExample {

    long startRange;
    long endRange;
    long counter = 0;
    static long MAX_NUM = MAX_VALUE;

    public MTSumExample(long startRange, long endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }

    public void add() {
        for (long i = startRange; i < endRange; i++) {
            counter += i;
        }
    }

    public static void twoThreads() throws InterruptedException {
        long start = currentTimeMillis();
        // each example does half the work of the example in oneThread()
        MTSumExample example1 = new MTSumExample(1, MAX_NUM / 2);
        MTSumExample example2 = new MTSumExample(1 + (MAX_NUM / 2), MAX_NUM);

        Thread thread1 = new Thread(example1::add);
        Thread thread2 = new Thread(example2::add);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        long finalCount = example1.counter + example2.counter;
        long end = currentTimeMillis();
        System.out.println("Two threads final count = " + finalCount + " took " + (end - start));
    }


    public static void oneThread() {
        long start = currentTimeMillis();
        MTSumExample example = new MTSumExample(1, MAX_NUM);
        example.add();
        long end = currentTimeMillis();
        System.out.println("One thread final count = " + example.counter + " took " + (end - start));
    }

    public static void runTest() throws InterruptedException {
        oneThread();
        twoThreads();
    }

    public static void main(String[] args) throws InterruptedException {
        MTSumExample.runTest();
    }
}
