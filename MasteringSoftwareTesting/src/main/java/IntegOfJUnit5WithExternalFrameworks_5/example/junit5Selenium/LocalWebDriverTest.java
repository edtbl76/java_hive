package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Selenium;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumExtension.class)
public class LocalWebDriverTest {

    @Test
    public void testWithChrome(ChromeDriver chromeDriver) {
        chromeDriver.get("https://emangini.com");
        assertTrue(chromeDriver.getTitle().startsWith("Home"));
    }

    @Test
    public void testWithFirefoxAndOpera(FirefoxDriver firefoxDriver, OperaDriver operaDriver) {
        firefoxDriver.get("http://www.seleniumhq.org/");
        assertTrue(firefoxDriver.getTitle().startsWith("Selenium"));

        operaDriver.get("http://junit.org/junit5/");
        assertEquals(operaDriver.getTitle(), "JUnit 5");
    }

    @Test
    public void testWithHeadlessBrowsers(HtmlUnitDriver htmlUnitDriver, PhantomJSDriver phantomJSDriver) {
        htmlUnitDriver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertTrue(htmlUnitDriver.getTitle().contains("JUnit 5 extension"));

        phantomJSDriver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertNotNull(phantomJSDriver.getPageSource());
    }
}
