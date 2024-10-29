package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "//*[contains(@text,'{SUBSTRING}')]",
            SAVE_BTN = "//*[@content-desc='Save']",
            ADD_TO_LIST_BTN = "//*[@text='Add to list']",
            TEXT_INPUT = "//*[@resource-id='org.wikipedia:id/text_input']",
            OK_BTN = "//*[@resource-id='android:id/button1']",
            MY_LISTS = "//*[@text='View list']",
            BACK_BUTTON = "//*[@content-desc='Navigate up']";

    private static String getArticleTitleWithSubsrting(String substring) {
        return TITLE.replace("{SUBSTRING}", substring);
    }

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public String getArticleTitle(String substring) {
        String title = getArticleTitleWithSubsrting(substring);
        return this.waitForElementPresent(By.xpath(title),"Cannot find article title", 5).getAttribute("text");
    }

    public void saveArticleToMyNewList(String folder) {
        this.waitForElementAndClick(By.xpath(SAVE_BTN),
                "Cannot find Save button",
                5);
        this.waitForElementAndClick(By.xpath(ADD_TO_LIST_BTN),
                "Cannot find save to list button",
                5);
        this.waitForElementAndSendKeys(By.xpath(TEXT_INPUT),
                folder,
                "Cannot find text input",
                5);
        this.waitForElementAndClick(By.xpath(OK_BTN),
                "Cannot find Ok buttton",
                5);
    }

    public void saveArticleToMyList(String folder) {
        this.waitForElementAndClick(By.xpath("//*[@content-desc='Save']"),
                "Cannot find Save button",
                5);
        this.waitForElementAndClick(By.xpath("//*[@text='Add to list']"),
                "Cannot find save to list button",
                5);
        this.waitForElementAndClick(By.xpath("//*[@text='" + folder + "']"),
                "Cannot find folder to save article",
                5);
    }

    public void clickViewLists() {
        this.waitForElementAndClick(By.xpath(MY_LISTS),
                "Cannot find View List button",
                5);
    }

    public void exitFromArticleBackToSearch() {
        this.waitForElementAndClick(By.xpath(BACK_BUTTON),"Cannot find back buttton",5);
    }
}
