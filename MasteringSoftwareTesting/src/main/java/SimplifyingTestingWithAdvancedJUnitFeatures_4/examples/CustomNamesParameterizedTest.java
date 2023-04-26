package SimplifyingTestingWithAdvancedJUnitFeatures_4.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CustomNamesParameterizedTest {

    @DisplayName("Display Name of Test Container")
    @ParameterizedTest(name = "[{index}] first argument=\"{0}\", second argument = {1}")
    @CsvSource({
            "Russell, 1",
            "Wilson, 2",
            "Quarterback, 3"
    })
    void testWithCustomDisplayNames(String first, int second) {
        System.out.println("Testing with parameters: " + first + " and " + second);
    }
}
