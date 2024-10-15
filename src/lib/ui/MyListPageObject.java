package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {

    private static final String
            TEXT_CONTAINS_TPL = "//*[@text = '{ARCTICLE_TITLE}']";

    private static String getTitleWithSubsrting(String article_title) {
        return TEXT_CONTAINS_TPL.replace("{ARCTICLE_TITLE}", article_title);
    }

    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void assertArticleInList(String article_title) {
        String title_xpath = getTitleWithSubsrting(article_title);
        this.waitForElementPresent(
                By.xpath(title_xpath), "Cannot find article in list",5);
    }

    public void swipeArticleToDelete(String article_title) {
        String title_xpath = getTitleWithSubsrting(article_title);
        this.swipeElementToLeft(By.xpath(title_xpath), "Cannot find article", 300);
        this.assertArticleNotPresentInList(article_title);
    }

    public void assertArticleNotPresentInList(String article_title) {
        String title_xpath = getTitleWithSubsrting(article_title);
        this.waitForElementNotPresent(By.xpath(title_xpath), "Article is still here", 5);
    }

    public void openArticleFromList(String article_title) {
        String title_xpath = getTitleWithSubsrting(article_title);
        this.waitForElementAndClick(By.xpath(title_xpath), "Cannot find article", 5);
    }
}
