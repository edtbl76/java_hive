package com.concurrency.AdvancedConcurrency.CountDownLatch;

public class CDLMain   {
    public static void main(String[] args) {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("External services validation completed!! Result was :: " + result);
    }
}
