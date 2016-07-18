package upwork.configs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created with Intellij IDEA
 * User: filosof_77
 * Date: 15.07.16
 * Time: 15:18
 */
public class DriverFramework {
    private WebDriver driver;

    private WebDriver createLocalDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/filosof_77/Desktop/chromedriver");
        WebDriver driver = new ChromeDriver();
        return driver;
    }


    WebDriver getDriver() {
        // 1. WebDriver instance is not created yet
        if (driver == null) {
            return createLocalDriver();
        }
        // 2. Browser is dead
        try {
            driver.getCurrentUrl();
        } catch (Throwable t) {
            t.printStackTrace();
            return createLocalDriver();
        }
        // . Just use existing WebDriver instance
        return driver;
    }

    public void quitDriver() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver = null;
    }
}
