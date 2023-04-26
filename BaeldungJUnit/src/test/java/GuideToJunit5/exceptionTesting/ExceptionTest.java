package GuideToJunit5.exceptionTesting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionTest {

    /*
        Verifies additional details of thrown exceptions.
     */
    @Test
    void shouldThrow() {
        Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException("Self Explanatory");
        });
        assertEquals(exception.getMessage(), "Self Explanatory");
    }

    /*
        Validates Exception Type
     */
    @Test
    void assertThrowException() {
        String str = null;
        assertThrows(IllegalArgumentException.class, () -> { Integer.valueOf(str);});
    }
}

