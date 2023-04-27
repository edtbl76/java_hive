package multithreading.threads.threadlocal;

import utils.Generated;

@Generated
public class ThreadLocalSafeCounter {

    ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> 0);
    void increment() {
        counter.set(counter.get() + 1);
    }
}
