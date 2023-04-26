package SimplifyingTestingWithAdvancedJUnitFeatures_4.examples;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ALL;

public class EnumSourceFilteringParameterizedTest {

    /*
        This is a default EnumSource (INCLUDE)
        - When this executes it will only display the nums that match 'names'
     */
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, names = {"DAYS", "HOURS"})
    void testWithFilteredEnum(TimeUnit argument) {
        System.out.println("Parameterized test with included (TimeUnit) arguments: " + argument);
        assertNotNull(argument);
    }

    /*
        This is EnumSource w/ EXCLUSION mode.
        - this includes everything BUT the provided values.
     */
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = EXCLUDE, names = {"DAYS", "HOURS"})
    void testWithExcludedEnum(TimeUnit argument) {
        System.out.println("Parameterized test without excluded (TimeUnit) arguments: " + argument);
        assertNotNull(argument);
    }

    /*
        This is EnumSource w/ a Regex match.
        - This matches anything starting w/ M/N that ends in seconds.
            - (for our purposes this includes subdivisions of a second)
     */
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = MATCH_ALL, names = {"^(M|N).+SECONDS$"})
    void testWithRegexEnum(TimeUnit argument) {
        System.out.println("Parameterized test with matching (TimeUnit) arguments: " + argument);
        assertNotNull(argument);
    }

}
