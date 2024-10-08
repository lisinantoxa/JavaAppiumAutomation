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

import java.net.URL;

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

    @Test
    public void testSearchResultText() {
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
                "Java",
                "Cannot find search input",
                5);
        WebElement first_article = waitForElementPresent(
                By.xpath("(//*[@resource-id='org.wikipedia:id/page_list_item_title'])[1]"),
                "Cannot find search result",
                5);
        assertElementContainsText(
                first_article,
                "Java"
        );
        WebElement second_article = waitForElementPresent(
                By.xpath("(//*[@resource-id='org.wikipedia:id/page_list_item_title'])[2]"),
                "Cannot find search result",
                5);
        assertElementContainsText(
                second_article,
                "Java"
        );
        WebElement third_article = waitForElementPresent(
                By.xpath("(//*[@resource-id='org.wikipedia:id/page_list_item_title'])[3]"),
                "Cannot find search result",
                5);
        assertElementContainsText(
                third_article,
                "Java"
        );
        WebElement fourth_article = waitForElementPresent(
                By.xpath("(//*[@resource-id='org.wikipedia:id/page_list_item_title'])[4]"),
                "Cannot find search result",
                5);
        assertElementContainsText(
                fourth_article,
                "Java"
        );
        WebElement fifth_article = waitForElementPresent(
                By.xpath("(//*[@resource-id='org.wikipedia:id/page_list_item_title'])[5]"),
                "Cannot find search result",
                5);
        assertElementContainsText(
                fifth_article,
                "Java"
        );
        WebElement sixth_article = waitForElementPresent(
                By.xpath("(//*[@resource-id='org.wikipedia:id/page_list_item_title'])[6]"),
                "Cannot find search result",
                5);
        assertElementContainsText(
                sixth_article,
                "Java"
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement assertElementHasText(WebElement element, String expected_text, String error_message) {
        String element_text = element.getAttribute("text");
        Assert.assertEquals(
                error_message,
                expected_text,
                element_text
        );
        return element;
    }

    private WebElement assertElementContainsText(WebElement element, String expected_text) {
        String element_text = element.getAttribute("text");
        Assert.assertTrue(element_text.contains(expected_text));
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
