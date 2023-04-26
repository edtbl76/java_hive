package com.concurrency.AdvancedConcurrency.CountDownLatch;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ApplicationStartupUtil {

    // List of service checkers
    private static List<BaseHealthChecker> _services;

    // latch used for waiting
    private static CountDownLatch _latch;

    private ApplicationStartupUtil() {}

    private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

    public static ApplicationStartupUtil getInstance() {
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws Exception {

        // Init latch w/ number of checkers
        _latch = new CountDownLatch(3);

        // create the checkers list
        _services = Arrays.asList(
                new NetworkHealthChecker(_latch),
                new CacheHealthChecker(_latch),
                new DatabaseHealthChecker(_latch));



        // Start service checkers
        Executor executor = Executors.newFixedThreadPool(_services.size());

        // I prefer this to enhanced for loop and lambdas. (ii.e. forEach + method expression_)
        _services.forEach(executor::execute);

        // Drain the latch
        _latch.await();


        AtomicBoolean result = new AtomicBoolean(false);
        _services.forEach(baseHealthChecker -> {
            result.set(baseHealthChecker.isServiceUp());
        });
        return result.get();
    }
}
