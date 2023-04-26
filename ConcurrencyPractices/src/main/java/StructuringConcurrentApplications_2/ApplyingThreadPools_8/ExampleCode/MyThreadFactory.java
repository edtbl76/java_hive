package StructuringConcurrentApplications_2.ApplyingThreadPools_8.ExampleCode;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new MyAppThread(runnable, poolName);
    }
}
