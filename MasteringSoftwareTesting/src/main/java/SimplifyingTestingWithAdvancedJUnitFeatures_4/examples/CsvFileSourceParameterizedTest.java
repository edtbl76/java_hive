package SimplifyingTestingWithAdvancedJUnitFeatures_4.examples;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CsvFileSourceParameterizedTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/input.csv")
    void testWithCsvFileSource(String first, int second) {
        System.out.println("Another parameterized test with (String) " + first + " and (int) " + second);
        assertNotNull(first);
        assertNotEquals(0, second);
    }
}
