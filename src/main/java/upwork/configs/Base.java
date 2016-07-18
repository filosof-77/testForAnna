package upwork.configs;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import upwork.pages.Page;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created with Intellij IDEA
 * User: filosof_77
 * Date: 15.07.16
 * Time: 16:49
 */
public class Base {
    private final DriverFramework framework = new DriverFramework();
    private WebDriver driver;

    public static <T extends Page> T getPage(WebDriver driver, Class<T> pageClass) {
        final T page = instantiatePage(driver, pageClass);
        page.driver = driver;
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.startsWith("https://")) {
            Reporter.log("httpS Issue on page: " + currentUrl);
        }
        return page;
    }

    private static <T> T instantiatePage(WebDriver driver, Class<T> pageClassToProxy) {
        try {
            try {
                Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
                return constructor.newInstance(driver);
            } catch (NoSuchMethodException e) {
                return pageClassToProxy.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param pageClass PageObject class, extends <code>com.upwork.pages.Page.class</code>
     * @param <T>       returned Page Object class
     * @return instance of Page Object class
     */
    protected <T extends Page> T openPage(String path, Class<T> pageClass) {
        return getPage(path, pageClass);
    }

    /**
     * @param <T>       returned Page Object class
     * @param path      The path to resource (for example, "Params.ACCOUNT_MANAGE_HOST or Params.UPWORK_URL" etc. in future.... =) ).
     * @param pageClass PageObject class, extends <code>com.upwork.pages.Page.class</code>
     */
    private <T extends Page> T getPage(String path, Class<T> pageClass) {
        String url;
        url = "https://" + path;
        driver.get(url);
        Reporter.log("Open URL: " + url + "\tPage Class: " + pageClass.getName(), 2, true);
        return getPage(driver, pageClass);
    }

    void setUPBrowser() {
        driver = framework.getDriver();
    }

    @AfterTest(alwaysRun = true)
    public void tearDownTest() {
        framework.quitDriver();
    }
}
