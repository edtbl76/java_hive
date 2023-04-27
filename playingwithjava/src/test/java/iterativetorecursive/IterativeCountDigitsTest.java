package iterativetorecursive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IterativeCountDigitsTest {

    @Test
    void testCountDigits() {
        int result = IterativeCountDigits.countDigits(1435043);
        Assertions.assertEquals(7, result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme