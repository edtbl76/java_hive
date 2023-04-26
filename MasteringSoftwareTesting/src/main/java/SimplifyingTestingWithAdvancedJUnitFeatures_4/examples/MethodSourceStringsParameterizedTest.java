package SimplifyingTestingWithAdvancedJUnitFeatures_4.examples;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MethodSourceStringsParameterizedTest {

    static Stream<String> stringProvider() {
       return Stream.of("a", "b", "c");
    }


    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithStringProvider(String argument) {
        System.out.println("Parameterized test with (String) argument: " + argument);
        assertNotNull(argument);
    }
}
