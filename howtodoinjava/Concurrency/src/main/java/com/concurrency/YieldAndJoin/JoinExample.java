package com.concurrency.YieldAndJoin;

public class JoinExample {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
               System.out.println("First task started");
               System.out.println("Sleeping for 2 seconds");
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException ex) {
                   ex.printStackTrace();
               }
               System.out.println("First task completed");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Second task completed");
            }
        });

        t1.start();
        t1.join();
        t2.start();
    }
}
