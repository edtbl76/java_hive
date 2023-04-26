package GuideToJunit5.assumptions;

import org.junit.jupiter.api.Test;
import org.opentest4j.TestAbortedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;

public class assumptions5 {

    /*
        GuideToJunit5.assumptions are used to handle external conditions that allow a test to run correctly.
        The purpose of the assumption is to CONDITIONALLY run the assertion.

     */
    @Test
    public void trueAssumption() {
        assumeTrue(5 > 1);
        assertEquals(5 + 2, 7);
    }

    @Test
    public void falseAssumption() {
        assumeFalse( 5 < 1);
        assertEquals( 5 + 2, 7);
    }

    @Test
    public void thatAssumption() {
        String input = "input";
        assumingThat(input.equals("input"), () -> assertEquals(2 + 2, 4));
    }

    @Test
    public void badAssumption() {
        try {
            assumeTrue(5 < 2);
        } catch (TestAbortedException throwable) {
            // This throws an exception, because the assumption isn't true.
            // Any tests are skipped
            throwable.printStackTrace();
        }
    }
}

