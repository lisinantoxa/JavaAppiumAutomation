import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.MyListPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.*;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearchFieldText() {
        MainPageObject.skipOnboarding();
        WebElement element = MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search field",
                5);
        MainPageObject.assertElementHasText(
                element,
                "Search Wikipedia",
                "Text in search field incorrect"
        );
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        MainPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.waitForSearchResultDisplayAppear();
        SearchPageObject.cancelSearch();
        SearchPageObject.waitForSearchResultDisplayDisappear();
    }

    @Test
    public void testSearchResultText() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        MainPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String first_article = SearchPageObject.getSearchResultByListItem("1");
        String second_article = SearchPageObject.getSearchResultByListItem("2");
        String third_article = SearchPageObject.getSearchResultByListItem("3");
        String fourth = SearchPageObject.getSearchResultByListItem("4");
        String fifth_article = SearchPageObject.getSearchResultByListItem("5");
        String[] search_results =
                { first_article, second_article, third_article, fourth, fifth_article};
        for (String search_result : search_results) {
            assertTrue(search_result.contains("Java"));
        }
    }

    @Test
    public void testSaveTwoArticleToMyListAndDeleteOne() {
        String folder_name = "Test";
        String first_article = "Java (programming language)";
        String second_article = "Java version history";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MainPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult(first_article);
        SearchPageObject.clickByArticleBySubstring(first_article);
        ArticlePageObject.saveArticleToMyNewList(folder_name);
        ArticlePageObject.exitFromArticleBackToSearch();
        SearchPageObject.waitForSearchResult(second_article);
        SearchPageObject.clickByArticleBySubstring(second_article);
        ArticlePageObject.saveArticleToMyList(folder_name);
        ArticlePageObject.clickViewLists();
        MyListPageObject.assertArticleInList(first_article);
        MyListPageObject.assertArticleInList(second_article);
        MyListPageObject.swipeArticleToDelete(second_article);
        MyListPageObject.openArticleFromList(first_article);
        String title = ArticlePageObject.getArticleTitle(first_article);
        assertEquals("We see unexpected result", first_article, title);
    }

    @Test
    public void testArticleTitlePresent() {
        String article = "Java (programming language)";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        MainPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult(article);
        SearchPageObject.clickByArticleBySubstring(article);
        String title = ArticlePageObject.getArticleTitle(article);
        assertEquals("We see unexpected result", article, title);
    }

    @Test
    public void testArticleTitleAndDescriptionPresent() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        MainPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Island in Indonesia");
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
        SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "High-level programming language");
    }
}
