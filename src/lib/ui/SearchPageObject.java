package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "search_src_text",
        BACK_BUTTON = "//*[@content-desc='Navigate up']",
        SEARCH_DISPLAY = "//*[@resource-id='org.wikipedia:id/search_results_display']",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[contains(@text,'{SUBSTRING}')]",
        SEARCH_RESULT_BY_NUMBER_TPL = "(//*[@resource-id='org.wikipedia:id/page_list_item_title'])[{NUMBER}]";

    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchByNumber(String number) {
        return SEARCH_RESULT_BY_NUMBER_TPL.replace("{NUMBER}", number);
    }

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after click search element", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), search_line,"Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring, 5);
    }

    public void waitForSearchResultDisplayAppear() {
        this.waitForElementPresent(By.xpath(SEARCH_DISPLAY), "Cannot find search display element", 5);
    }

    public void waitForSearchResultDisplayDisappear() {
        this.waitForElementNotPresent(By.xpath(SEARCH_DISPLAY), "Search results display still here", 5);
    }

    public void cancelSearch() {
        this.waitForElementAndClick(By.xpath(BACK_BUTTON),"Cannot find back buttton",5);
    }

    public void clickByArticleBySubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 5);
    }

    public String getSearchResultByListItem(String number) {
        String number_xpath = getResultSearchByNumber(number);
        return this.waitForElementPresent(By.xpath(number_xpath),"Cannot find search result by list item", 5).getAttribute("text");
    }
}
