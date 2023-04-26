package SimplifyingTestingWithAdvancedJUnitFeatures_4.examples;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ValueSourcePrimitiveTypesParameterizedTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void testWithInts(int argument) {
        System.out.println("Parameterized Test with (int) argument: " + argument);
        assertNotNull(argument);
    }

    @ParameterizedTest
    @ValueSource(longs = {2L, 3L})
    void testWithLongs(long argument) {
        System.out.println("Parameterized Test with (long) argument: " + argument);
        assertNotNull(argument);
    }

    @ParameterizedTest
    @ValueSource(doubles = {4d, 5d})
    void testWithDoubles(double argument) {
        System.out.println("Parameterized Test with (double) argument: " + argument);
        assertNotNull(argument);
    }
}
