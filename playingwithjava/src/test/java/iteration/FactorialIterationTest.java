package iteration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorialIterationTest {

    @Test
    void testFactorialIterative() {
        int result = FactorialIteration.factorialIterative(5);
        assertEquals(120, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme