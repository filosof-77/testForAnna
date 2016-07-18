package upwork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

/**
 * Created with Intellij IDEA
 * User: filosof_77
 * Date: 15.07.16
 * Time: 18:48
 */
public class Page {
    public WebDriver driver;

    public WebElement findElement(By by) {
        try {
            waitFor(ExpectedConditions.visibilityOfElementLocated(by), 5);
        } catch (Exception ignored) {
        }
        return driver.findElement(by);
    }

    public void waitFor(ExpectedCondition<?> condition, final int sec) {
        try {
            new FluentWait<>(driver).
                    withTimeout(sec, TimeUnit.SECONDS).
                    pollingEvery(500, TimeUnit.MILLISECONDS).
                    ignoring(NoSuchElementException.class).
                    until(condition);
        } catch (Exception ignored) {
        }
    }

    public void waitFor(ExpectedCondition<?> condition) {
        waitFor(condition, 10);
    }

    public WebElement findElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public boolean isElementPresent(String xpath) {
        Reporter.log("Check element " + xpath + " present on page " + driver.getCurrentUrl() + "\n");
        return driver.findElements(By.xpath(xpath)).size() > 0;
    }

    public java.util.List<WebElement> findElements(By by) {
        Reporter.log("Find elements: \"" + by.toString() + "\" on page \"" + driver.getCurrentUrl() + "\"\n");
        return driver.findElements(by);
    }

}
