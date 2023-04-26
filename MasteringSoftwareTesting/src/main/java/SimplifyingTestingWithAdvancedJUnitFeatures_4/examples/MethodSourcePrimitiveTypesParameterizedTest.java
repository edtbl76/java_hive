package SimplifyingTestingWithAdvancedJUnitFeatures_4.examples;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MethodSourcePrimitiveTypesParameterizedTest {

    @ParameterizedTest
    @MethodSource("intProvider")
    void testWithIntProvider(int argument) {
        System.out.println("Parameterized Test with (int) argument: " + argument);
        assertNotNull(argument);
    }

    @ParameterizedTest
    @MethodSource("doubleProvider")
    void testWithDoubleProvider(double argument) {
        System.out.println("Parameterized Test with (double) argument: " + argument);
        assertNotNull(argument);
    }

    @ParameterizedTest
    @MethodSource("longProvider")
    void testWithLongProvider(long argument) {
        System.out.println("Parameterized Test with (long) argument: " + argument);
        assertNotNull(argument);
    }

    /*
        Utility Stuff
     */
    static IntStream intProvider() {
        return IntStream.of(0, 1);
    }

    static DoubleStream doubleProvider() {
        return DoubleStream.of(2d, 3d);
    }

    static LongStream longProvider() {
        return LongStream.of(4L, 5L);
    }
}
