package com.emangini.e2e;

import io.github.bonigarcia.seljup.Arguments;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith({SpringExtension.class, SeleniumJupiter.class})
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DisplayName("E2E:UI")
@Tag("e2e")
class UITest {

    @LocalServerPort
    int serverPort;

    @Test
    @DisplayName("List dogs in GUI")
    void testListDogs(ChromeDriver chromeDriver) {
        chromeDriver.get("http://localhost:" + serverPort);
        List<WebElement> dogLinks = chromeDriver
                .findElements(By.className("lightbox"));
        assertThat(dogLinks.size(), equalTo(17));
    }


    @Test
    @DisplayName("Rate a dog using GUI")
    void testRateDog(FirefoxDriver firefoxDriver) {
       firefoxDriver.get("http://localhost:" + serverPort);
        firefoxDriver.findElement(By.id("LazyCouch")).click();

        String fourStarsSelector = "#form1 span:nth-child(3)";
        new WebDriverWait(firefoxDriver, 15)
                .until(elementToBeClickable(By.cssSelector(fourStarsSelector)));
        firefoxDriver.findElement(By.cssSelector(fourStarsSelector)).click();


        firefoxDriver.findElement(By.xpath("//*[@id=\"comment\"]"))
                .sendKeys("Wonderful Puppy");
        firefoxDriver.findElement(By.cssSelector("#form1> button")).click();

        WebElement successDiv = firefoxDriver
                .findElement(By.cssSelector("#success > div"));
        assertThat(successDiv.getText(), containsString("Your rating for LazyCouch"));
    }

    @Test
    @DisplayName("Rate a dog using GUI w/ error")
    void testRateDogWithError(
            @Arguments("--headless") ChromeDriver chromeDriver) {
        chromeDriver.get("http://localhost:" + serverPort);
        chromeDriver.findElement(By.id("LazyCouch")).click();

        String sendButtonSelector = "#form1 > button";
        new WebDriverWait(chromeDriver, 10)
                .until(elementToBeClickable(By.cssSelector(sendButtonSelector)));
        chromeDriver.findElement(By.cssSelector(sendButtonSelector)).click();

        WebElement successDiv = chromeDriver
                .findElement(By.cssSelector("#error > div"));
        assertThat(successDiv.getText(), containsString("You need to select stars for rating dogs."));
    }


}
