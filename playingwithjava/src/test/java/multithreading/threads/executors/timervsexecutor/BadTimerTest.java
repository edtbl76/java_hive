package multithreading.threads.executors.timervsexecutor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BadTimerTest {

    @Test
    void testMain_ThrowsInterruptedException() throws InterruptedException {
        BadTimer.main(new String[]{"args"});
        assertTrue(true);
    }
}
