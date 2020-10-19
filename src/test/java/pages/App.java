package pages;

import constants.PropertyConfigs;
import elements.Button;
import elements.DropDown;
import elements.Label;
import org.openqa.selenium.By;
import utils.ConfigReader;
import utils.WebDriverUtil;

/**
 * Represents Craigslist Finland website main page
 */
public class App {

    public static DropDown drpLanguage = new DropDown(By.id("chlang"));
    public static Label lblTopBanner = new Label(By.cssSelector(".homepage #topban"));
    public static Button btnHousing = new Button(By.cssSelector(".housing #hhh"));

    public static void openApp() {
        WebDriverUtil.getDriver().navigate().to(ConfigReader.getInstance().getValue(PropertyConfigs.APP_URL));
    }
}
