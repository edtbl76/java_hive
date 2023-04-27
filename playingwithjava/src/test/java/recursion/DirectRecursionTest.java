package recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DirectRecursionTest {

    @Test
    void testSquare() {
        int result = DirectRecursion.square(6);
        Assertions.assertEquals(36, result);
    }



}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme