package utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.DecimalFormat;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.math.RoundingMode.UP;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Utils.*;

class UtilsTest {

    @Test
    // This covers three branches TT, TF, FT
    void testLeftTrim() {
        String result = leftTrim("    string");
        assertEquals("string", result);
    }

    // This covers the case where the method never enters the while loop.
    @Test
    void testLeftTrimEmpty() {
        assertEquals("", leftTrim(""));
    }

    @Test
    void testRightTrim() {
        String result = rightTrim("string    ");
        assertEquals("string", result);
    }

    @Test
    void testRightTrimEmtpy() {
        assertEquals("", rightTrim(""));
    }

    @Test
    void testSwap() {
        int[] array = new int[]{1, 4, 3, 2, 5};
        int[] expected = new int[]{1, 2, 3, 4, 5};
        swap(array, 1, 3);
        assertArrayEquals(array, expected);
    }

    /*
        The next two tests are for Amdahl's Law and utilization:
        - demonstrates that as processors goes up
            - the speed up increases
            - utilization goes down (and CPUs are sitting around idle)
     */
    @ParameterizedTest
    @CsvSource(value = {"1:1", "2:1.82", "5:3.58", "10:5.27", "100:9.18", "1000:9.92"}, delimiter = ':')
    void testAmdahlsLawCalculator(String threads, String expected) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(UP);

        String formattedExpected = decimalFormat.format(parseDouble(expected));

        double result = calculateAmdahlsLaw(parseInt(threads), 0.9);
        String formattedResult = decimalFormat.format(result);
        assertEquals(formattedExpected, formattedResult);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:1", "2:0.91", "5:0.72", "10:0.53", "100:0.1", "1000:0.01"}, delimiter = ':')
    void testCalculateUtilization(String threads, String expected) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(UP);

        String formattedExpected = decimalFormat.format(parseDouble(expected));

        double result = calculateUtilization(parseInt(threads), 0.9);
        String formattedResult = decimalFormat.format(result);
        assertEquals(formattedExpected, formattedResult);
    }

    @Test
    void testSummation() {
        assertEquals(15, summation(5));
    }
}
