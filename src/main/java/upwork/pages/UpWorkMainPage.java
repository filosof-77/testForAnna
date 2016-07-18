package upwork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import upwork.configs.Base;

/**
 * Created with Intellij IDEA
 * User: filosof_77
 * Date: 15.07.16
 * Time: 17:10
 */
public class UpWorkMainPage extends Page {

    public UpWorkMainPage enterSearchWord(String searchWord) {
        findElement(By.xpath("(//div[@class='input-group']//input[@name='q'])[1]")).sendKeys(searchWord);
        return this;
    }

    public SearchResultsPage submitSearch() {
        findElementByXpath("(//div[@class='input-group']//input[@name='q'])[1]").sendKeys(Keys.RETURN);
        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='main-search']")));
        return Base.getPage(driver, SearchResultsPage.class);
    }
}
