package SimplifyingTestingWithAdvancedJUnitFeatures_4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.util.HashMap;

public class TestReporterTest {

    @Test
    void reportSingleValue(TestReporter testReporter) {
        testReporter.publishEntry("key", "value");
    }

    @Test
    void reportSeveralValues(TestReporter testReporter) {
        HashMap<String, String> values = new HashMap<>();
        values.put("firstname", "ed");
        values.put("lastname", "mangini");
        testReporter.publishEntry(values);
    }
}
