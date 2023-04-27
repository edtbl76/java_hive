package multithreading.examples;

import utils.Generated;

@SuppressWarnings("java:S106")
@Generated
public class InterruptExample {


    public static void example() throws InterruptedException {
        final Thread thread = new Thread(() -> {
           try {
               System.out.println("Sleeping for an hour");
               Thread.sleep(3_600_000);
           } catch (InterruptedException e) {
               System.out.println("Clearing interrupt flag: " + Thread.interrupted() + " " +
                       Thread.currentThread().isInterrupted());

               Thread.currentThread().interrupt();
               System.out.println("Someone woke me up!");
               System.out.println("The interrupt flag is set now: " + Thread.currentThread().isInterrupted() +
                       " " + Thread.interrupted());
           }
        });

        thread.start();
        System.out.println("About to wake up the sleepy thread...");
        thread.interrupt();
        System.out.println("Woke up sleepy thread ... ");
        thread.join();
    }
    public static void main(String[] args) throws InterruptedException {
        InterruptExample.example();
    }
}
