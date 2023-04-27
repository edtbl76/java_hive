package multithreading.threads.callables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DemoSumTask2Test {

    private DemoSumTask2 demoSumTask2UnderTest;

    @BeforeEach
    void setUp() {
        demoSumTask2UnderTest = new DemoSumTask2();
    }

    @Test
    void setDemoSumTask2UnderTest() throws Exception {
        Callable<Integer> result = demoSumTask2UnderTest.sumTask;
        assertEquals(55, result.call());
    }
}
