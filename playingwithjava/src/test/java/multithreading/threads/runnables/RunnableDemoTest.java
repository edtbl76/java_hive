package multithreading.threads.runnables;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RunnableDemoTest {

    @Test
    void testMain() {
        RunnableDemo.main(new String[]{"args"});
        assertTrue(true);

    }
}
