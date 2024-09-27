import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
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

    @Test
    public void saveTwoArticleToMyListAndDeleteOne() {
        String folder_name = "Test";
        String first_article = "Java (programming language)";
        String second_article = "Java version history";
        waitForElementAndClick(
                By.id("fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5);
        waitForElementPresent(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search field",
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
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'"+ first_article +"')]"),
                "Cannot find search result",
                5);
        waitForElementAndClick(By.xpath("//*[@content-desc='Save']"),
                "Cannot find Save button",
                5);
        waitForElementAndClick(By.xpath("//*[@text='Add to list']"),
                "Cannot find save to list button",
                5);
        waitForElementAndSendKeys(By.xpath("//*[@resource-id='org.wikipedia:id/text_input']"),
                folder_name,
                "Cannot find text input",
                5);
        waitForElementAndClick(By.xpath("//*[@resource-id='android:id/button1']"),
                "Cannot find Ok buttton",
                5);
        waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot find back button",
                5);
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'"+ second_article +"')]"),
                "Cannot find search result",
                5);
        waitForElementAndClick(By.xpath("//*[@content-desc='Save']"),
                "Cannot find Save button",
                5);
        waitForElementAndClick(By.xpath("//*[@text='Add to list']"),
                "Cannot find save to list button",
                5);
        waitForElementAndClick(By.xpath("//*[@text='"+ folder_name +"']"),
                "Cannot find folder to save article",
                5);
        waitForElementAndClick(By.xpath("//*[@text='View list']"),
                "Cannot find View List button",
                5);
        waitForElementPresent(
                By.xpath("//*[contains(@text,'"+ first_article +"')]"),
                "Cannot find article in list",
                5);
        waitForElementPresent(
                By.xpath("//*[contains(@text,'"+ second_article +"')]"),
                "Cannot find article in list",
                5);
        swipeElementToLeft(By.xpath("//*[@text = '"+ second_article +"']"),
                "Cannot find search result",
                300);
        waitForElementNotPresent(By.xpath("//*[@text = '"+ second_article +"']"),
                "Article is still here",
                5);
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'"+ first_article +"')]"),
                "Cannot find article in list",
                5);
        WebElement article = waitForElementPresent(
                By.xpath("//*[@text = '"+ first_article +"']"),
                "Cannot find search result",
                5);
        assertElementContainsText(
                article,
                first_article
        );
    }

    @Test
    public void testArticleTitlePresent() {
        String article = "Java (programming language)";
        waitForElementAndClick(
                By.id("fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5);
        waitForElementPresent(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search field",
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
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'"+ article +"')]"),
                "Cannot find search result",
                5);
        assertElementPresent(By.xpath("//*[contains(@text,'"+ article +"')]"),
                "Cannot find title " + article);
    }

    private WebElement assertElementPresent(By by, String error_message) {
        List element = driver.findElements(by);
        Assert.assertFalse(error_message, element.isEmpty());
        return null;
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

    protected void swipeUp(int ms) {
        Dimension size =  driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 0);
        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, start_y));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(ms), PointerInput.Origin.viewport(), x, end_y));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(scroll));
    }

    protected void swipeUpQuick() {
        swipeUp(1500);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int swipes = 0;
        while (driver.findElements(by).isEmpty()) {
            if (swipes > max_swipes) {
                waitForElementPresent(by, error_message, 0);
                return;
            }
            ++swipes;
            swipeUpQuick();
        }
    }

    protected void swipeElementToLeft(By by, String error_message,int ms) {
        WebElement element = waitForElementPresent(by,
                error_message,
                5);
        Point sourceLocation = element.getLocation();
        Dimension sourceSize = element.getSize();
        int rightX = (int) (sourceLocation.getX() + sourceSize.getWidth() * 0.9);
        int leftX = sourceLocation.getX();
        int centerY = sourceLocation.getY() + sourceSize.getHeight() / 2;
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 0);
        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), rightX, centerY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(ms), PointerInput.Origin.viewport(), leftX, centerY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(scroll));
        }
}
