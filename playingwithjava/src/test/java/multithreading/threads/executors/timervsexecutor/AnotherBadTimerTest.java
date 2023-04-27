package multithreading.threads.executors.timervsexecutor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AnotherBadTimerTest {


    @Test
    void testMain_ThrowsInterruptedException() {
        // Setup
        // Run the test
        assertThrows(IllegalStateException.class, () -> AnotherBadTimer.main(new String[]{"args"}));
    }
}
