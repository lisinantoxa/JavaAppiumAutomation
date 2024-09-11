import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Test");
        capabilities.setCapability("platformVersion", "14");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\user\\Desktop\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\Wikipedia2.7.5.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSearchFieldText() {
        waitForElementAndClick(
                By.id("fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5);
        WebElement element = waitForElementPresent(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search field",
                5);
        assertElementHasText(
                element,
                "Search Wikipedia",
                "Text in search field incorrect"
        );
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5);
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search field",
                5);
        waitForElementAndSendKeys(
                By.id("search_src_text"),
                "Appium",
                "Cannot find search input",
                5);
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_display']"),
                "Cannot find search result",
                5);
        waitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot find back buttton",
                5);
        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_display']"),
                "Search results still here",
                5);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement assertElementHasText(WebElement element, String expected_message, String error_message) {
        String element_text = element.getAttribute("text");
        Assert.assertEquals(
                error_message,
                expected_message,
                element_text
        );
        return element;
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSec) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSec);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSec) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSec);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
