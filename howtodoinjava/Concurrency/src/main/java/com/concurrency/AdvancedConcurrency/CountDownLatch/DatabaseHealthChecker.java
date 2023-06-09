package com.concurrency.AdvancedConcurrency.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class DatabaseHealthChecker extends BaseHealthChecker {

    public DatabaseHealthChecker(CountDownLatch latch) {
        super("Database Service", latch);
    }

    @Override
    public void verifyService() {
        System.out.println("Checking..." + this.getServiceName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println(this.getServiceName() + ": UP");
    }
}
