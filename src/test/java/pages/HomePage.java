package pages;

import constants.PropertyConfigs;
import utils.ConfigReader;
import utils.WebDriverUtil;

/**
 * Represents Craigslist Finland website main page
 */
public class HomePage {
    public static void openApp() {
        WebDriverUtil.getDriver().navigate().to(ConfigReader.getInstance().getValue(PropertyConfigs.APP_URL));
    }
}
