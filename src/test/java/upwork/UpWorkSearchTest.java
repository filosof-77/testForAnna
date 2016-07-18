package upwork;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import upwork.configs.Params;
import upwork.configs.TestBase;
import upwork.enums.Sorting;
import upwork.pages.SearchResultsPage;
import upwork.pages.UpWorkMainPage;

import java.util.List;

import static org.testng.Assert.assertTrue;


/**
 * Created with Intellij IDEA
 * User: filosof_77
 * Date: 17.01.16.
 * Time: 18:39
 */
public class UpWorkSearchTest extends TestBase {
    private UpWorkMainPage upWorkMainPage;
    private SearchResultsPage searchResultsPage;
    private String searchWord;


    @BeforeTest
    public void setUpTest() throws Exception {
        searchWord = "Java";
        upWorkMainPage = openPage(Params.UPWORK_URL, UpWorkMainPage.class);
        upWorkMainPage.waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='input-group']//input[@name='q'])[1]")));


    }

    @Test(priority = 0)
    public void testEnterAndSubmitSearch() throws Exception {
        searchResultsPage = upWorkMainPage.enterSearchWord(searchWord)
                .submitSearch();
        assertTrue(searchResultsPage.isElementPresent("//div[@id='contractorTiles']"), "SearchResults is not visible.");
    }

    @Test(priority = 1)
    public void testSortingAndCheckResults() throws Exception {
        searchResultsPage.sortItemsByJobSuccess(Sorting.JOBS_90_UP);
        searchResultsPage.clickSearch();
        List<Integer> jobsSuccess = searchResultsPage.getSuccessRate();
        for (Integer percent : jobsSuccess) {
            assertTrue(percent >= 90, "Error! We looking for users with 'Success rate' > 90 but found user with rate: " + percent + "%");
        }
    }

    @Test(priority = 2)
    public void testCheckSkills() throws Exception {
        List<String> jobsSuccess = searchResultsPage.getSkills();
        for (String s : jobsSuccess) {
            assertTrue(s.contains("Java"), "Error! We looking for user with 'Java' skill but there is some user without it!");
        }
    }
}
