package recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FactorialRecursionTest {

    @Test
    void testFactorial() {
        int result = FactorialRecursion.factorial(6);
        Assertions.assertEquals(720, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme