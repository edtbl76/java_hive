package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Selenium;

import io.github.bonigarcia.seljup.DriverCapabilities;
import io.github.bonigarcia.seljup.DriverUrl;
import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumExtension.class)
public class RemoteWebDriverTest {

    @Test
    void testWithRemoteChrome(
            @DriverUrl("http://localhost:4444")
            @DriverCapabilities({"browserName=chrome",})
            RemoteWebDriver remoteChrome
    ) throws InterruptedException {
        Thread.sleep(5000);
        remoteChrome.get("https://emangini.com");
        assertTrue(remoteChrome.getTitle().contains("@emangini"));
    }
}
