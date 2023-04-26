package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "classpath:features"
)
public class CucumberTest {

    /*
        This class should be empty.
        Step Definitions are placed in a separate class.
     */

}
