package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Selenium;

import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.seljup.DriverCapabilities;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
@ExtendWith(SeleniumJupiter.class)
public class AppiumTest {

    @DriverCapabilities
    DesiredCapabilities capabilities = new DesiredCapabilities();
    {
        capabilities.setPlatform(Platform.ANDROID);
        capabilities.setBrowserName("Chrome");
        capabilities.setCapability("chromedriverExecutable", "/usr/local/lib/node_modules/appium-chromedriver/chromedriver/mac/chromedriver");
    }

    @Test
    void testWithAndroid(AppiumDriver<WebElement> android) {
        String context = android.getContext();
        System.out.println(context);
        android.context("NATIVE_APP");
        android.findElement(By.id("com.android.chrome:id/menu_button"))
                .click();
        android.findElement(By.id("com.android.chrome:id/button_four"))
                .click();
        android.context(context);
        android.get("https://emangini.com");
        assertTrue(android.getTitle().contains("emangini"));
    }
}
