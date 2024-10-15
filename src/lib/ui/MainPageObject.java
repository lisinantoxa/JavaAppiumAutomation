package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPageObject {

    private static final String
            SKIP_ELEMENT = "fragment_onboarding_skip_button";

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement assertElementPresent(By by, String error_message) {
        List element = driver.findElements(by);
        Assert.assertFalse(error_message, element.isEmpty());
        return null;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement assertElementHasText(WebElement element, String expected_text, String error_message) {
        String element_text = element.getAttribute("text");
        Assert.assertEquals(
                error_message,
                expected_text,
                element_text
        );
        return element;
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSec) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSec);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSec) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSec);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void swipeUp(int ms) {
        Dimension size = driver.manage().window().getSize();
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

    public void swipeUpQuick() {
        swipeUp(1500);
    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes) {
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

    public void swipeElementToLeft(By by, String error_message, int ms) {
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

    public void skipOnboarding() {
        this.waitForElementAndClick(By.id(SKIP_ELEMENT), "Cannot find and click skip btn", 5);
        this.waitForElementNotPresent(By.id(SKIP_ELEMENT), "Skip btn still here", 5);
    }
}
