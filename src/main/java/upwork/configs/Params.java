package upwork.configs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with Intellij IDEA
 * User: filosof_77
 * Date: 15.07.16
 * Time: 16:57
 */
public class Params {
    public static String UPWORK_URL;

    static {
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("upwork.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            UPWORK_URL = prop.getProperty("mainHost");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
