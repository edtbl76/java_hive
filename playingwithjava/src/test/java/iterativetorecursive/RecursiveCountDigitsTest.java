package iterativetorecursive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RecursiveCountDigitsTest {

    @Test
    void testCountDigits() {
        int result = RecursiveCountDigits.countDigits(1435043);
        Assertions.assertEquals(7, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme