package com.concurrency.AdvancedConcurrency.ThreadFactoryExamples;

import java.util.concurrent.TimeUnit;

public class TFTask implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
