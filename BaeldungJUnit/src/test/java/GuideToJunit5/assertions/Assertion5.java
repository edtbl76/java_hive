package GuideToJunit5.assertions;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class Assertion5 {

    @Test
    public void lambdaExpression() {
        assertTrue(Stream.of(2, 4, 6)
                .mapToInt(i -> i)
                .sum() > 5, () -> "Sum should be greater than 5");
    }


    @Test
    void groupAssertions() {
        int[] nums = {2, 4, 6, 8, 10};
        assertAll("numbers",
                () -> assertEquals(nums[0], 2),
                () -> assertEquals(nums[2], 6),
                () -> assertEquals(nums[4], 10)
        );
    }
}
