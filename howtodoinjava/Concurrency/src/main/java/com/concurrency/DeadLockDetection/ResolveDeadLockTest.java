package com.concurrency.DeadLockDetection;

public class ResolveDeadLockTest {

    // This causes a deadlock :)
    /*
        The solution to this SUCKS, but you can reorder the Runnable run() overrides so that both classes access
        the resources in the same order.
            - example block1 accesses b, then a
            - example block2 accesses b, then a.

            This means that block2 will start (because of the delay in block1). Block1 grabs b after block 2, and then
            technically block1 will lag block2 by a single resource.


     */
    public static void main(String[] args) {
        ResolveDeadLockTest test = new ResolveDeadLockTest();
        final A a = test.new A();
        final B b = test.new B();

        // Thread1
        Runnable block1 = new Runnable() {
            @Override
            public void run() {
                synchronized (a){
                    try {
                        // add delay so that both threads can start trying to lock resources
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    // Thread-1 has A, but needs B
                    synchronized(b) {
                        System.out.println("In block1");
                    }
                }
            }
        };

        Runnable block2 = new Runnable() {
            @Override
            public void run() {
                synchronized (b) {
                    // Thread-2 have B but need A also
                    synchronized (a) {
                        System.out.println("In block2");
                    }
                }
            }
        };

        new Thread(block1).start();
        new Thread(block2).start();
    }

    private class A {
        private int i = 30;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }

    private class B {
        private int i  = 20;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }
}
