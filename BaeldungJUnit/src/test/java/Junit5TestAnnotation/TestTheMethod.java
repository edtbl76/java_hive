package Junit5TestAnnotation;

import JUnit5TestAnnotation.MethodUnderTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTheMethod {

    @Test
    void givenEvenNumber_whenCheckIsNumberEven_thenTrue() {
        boolean result = MethodUnderTest.isNumberEven(10);
        assertTrue(result);
    }

    @Test
    void givenOddNumber_whenCheckIsNumberEven_thenFalse() {
        boolean result = MethodUnderTest.isNumberEven(3);
        assertFalse(result);
    }

}
