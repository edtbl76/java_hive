package SimplifyingTestingWithAdvancedJUnitFeatures_4.examples;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CsvSourceParameterizedTest {

    @ParameterizedTest
    @CsvSource({
            "Seattle, 1",
            "Seahawks, 2",
            "'12s, Forever', 3"
    })
    void testWithCsvSource(String first, int second) {
        System.out.println("Parameterized test with (String) " + first + " and (int) " + second);
        assertNotNull(first);
        assertNotEquals(0, second);
    }
}
