package upwork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import upwork.enums.Sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with Intellij IDEA
 * User: filosof_77
 * Date: 15.07.16
 * Time: 19:36
 */
public class SearchResultsPage extends Page {

    public void sortItemsByJobSuccess(Sorting sorting) {
        findElementByXpath("//*[@id='oContractorFacets']//div[@data-child-facetname='top_rated']").click();
        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'dropdown-toggle') and @aria-expanded='true']")));
        findElement(By.xpath(sorting.xpath)).click();
    }

    public void clickSearch() {
        findElement(By.xpath("//div[@id='oContractorSearch']//button[@data-o-log-click='keyword_search']")).click();
        waitFor(ExpectedConditions.numberOfElementsToBe(By.xpath("//o-job-success[@class='ng-isolate-scope']//span[@class='ng-binding']"), 10));
    }

    public List<Integer> getSuccessRate() {
        List<WebElement> elements = findElements(By.xpath("//o-job-success[@class='ng-isolate-scope']//span[@class='ng-binding']"));
        List<Integer> allMatches = new ArrayList<>();

        for (WebElement element : elements) {
            allMatches.add(Integer.valueOf(getMatchedPercent(element.getText())));
        }
        return allMatches;
    }

    public String getMatchedPercent(String s) {
        Pattern pattern = Pattern.compile("(\\d+)%");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    public List<String> getSkills() {
        List<WebElement> elements = findElements(By.xpath("//section[@class='air-card-hover']//ul[contains(@class,'list-inline')]"));
        ArrayList<String> skills = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            skills.add(elements.get(i).getAttribute("textContent"));
        }

        return skills;
    }
}
