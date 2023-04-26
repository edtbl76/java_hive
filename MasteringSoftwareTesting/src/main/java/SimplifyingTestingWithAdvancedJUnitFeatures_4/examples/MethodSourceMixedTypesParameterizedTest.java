package SimplifyingTestingWithAdvancedJUnitFeatures_4.examples;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MethodSourceMixedTypesParameterizedTest {


    @ParameterizedTest
    @MethodSource("stringAndIntProvider")
    void testWithMultiArgMethodSource(String first, int second) {
        System.out.println("Parameterized test with two arguments: " +
                "(String) " + first + " and (int) " + second);
        assertNotNull(first);
        assertNotEquals(0, second);
    }

    static Stream<Arguments> stringAndIntProvider() {
        return Stream.of(Arguments.of("Ed", 44), Arguments.of("Connor", 9), Arguments.of ("Mike", 20));
    }
}
