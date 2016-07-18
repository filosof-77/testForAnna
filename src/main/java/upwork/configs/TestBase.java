package upwork.configs;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Created with Intellij IDEA
 * User: filosof_77
 * Date: 15.07.16
 * Time: 16:49
 */
public class TestBase extends Base {

    @BeforeTest(alwaysRun = true)
    public void setUpMethod() {
        setUPBrowser();
    }

    @AfterTest
    @Override
    public void tearDownTest() {
        super.tearDownTest();
    }
}
