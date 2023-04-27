package recursion;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;
import static recursion.RecursionWithNumbers.*;

class RecursionWithNumbersTest {

    @ParameterizedTest
    @CsvSource(value = {"0:0", "1:1", "2:3", "100:5050"}, delimiter = ':')
    void testSumAllBase(String input, String expected) {
        assertEquals(parseInt(expected), sumAll(parseInt(input)));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0:0", "1:1", "2:1", "3:2", "4:3", "5:5", "6:8", "7:13", "8:21", "9:34", "10:55"
    }, delimiter = ':')
    void testFibonacciBase(String input, String expected) {
        assertEquals(parseInt(expected), fibonacci(parseInt(input)));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "56:42:14", "0:0:0", "100:3:1"
    }, delimiter = ':')
    void testGreatestCommonDivisor(String first, String second, String result) {
        assertEquals(parseInt(result), greatestCommonDivisor(parseInt(first), parseInt(second)));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1:1", "64:2", "81:3", "16:4"
    }, delimiter = ':')
    void testCheckPowerOfTrue(String base, String exponent) {
        assertTrue(checkPowerOf(parseInt(base),parseInt(exponent) ));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0:0", "65:2", "80:3", "17:4"
    }, delimiter = ':')
    void testCheckPowerOfFalse(String base, String exponent) {
        assertFalse(checkPowerOf(parseInt(base),parseInt(exponent) ));
    }
}
