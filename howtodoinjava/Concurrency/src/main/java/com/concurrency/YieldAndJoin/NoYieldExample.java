package com.concurrency.YieldAndJoin;

public class NoYieldExample {
    public static void main(String[] args) {
        Thread producer = new NYProducer();
        Thread consumer = new NYConsumer();

        producer.setPriority(Thread.MIN_PRIORITY);
        consumer.setPriority(Thread.MAX_PRIORITY);

        producer.start();
        consumer.start();
    }
}

class NYProducer extends Thread {

    public void run() {
        for(int i = 0; i < 5; i ++)
            System.out.println("I am producer : Produced Item: " + i);
    }
}

class NYConsumer extends Thread {

    public void run() {
        for (int i = 0; i < 5; i++)
            System.out.println("I am Consumer: Consumed item " + i);
    }
}
