package hook;

import constants.PropertyConfigs;
import helpers.LogHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriverException;
import utils.ConfigReader;
import utils.WebDriverSetup;
import utils.WebDriverUtil;

import java.net.MalformedURLException;

public class Hook {

    static {
        LogHelper.getLogger().info("URL: " + ConfigReader.getInstance().getValue(PropertyConfigs.APP_URL));
    }

    @Before
    public void initialize(Scenario scenario) throws MalformedURLException {
        if (WebDriverUtil.getDriver() != null) {
            try {
                WebDriverUtil.getDriver().getTitle();
            } catch (Exception wde) {
                LogHelper.getLogger().error("Some error in existing driver: ", wde);
                try {
                    WebDriverUtil.getDriver().quit();
                } catch (Exception e) {
                    LogHelper.getLogger().error("Some error on driver quit: ", e);
                }
                WebDriverUtil.setDriver(null);
            }
        } else {
            LogHelper.getLogger().info("Open browser: " + WebDriverUtil.driverType);
            WebDriverUtil.setDriver(WebDriverSetup.setupWebDriver(WebDriverUtil.driverType));
            WebDriverUtil.setImplicitWait();
            WebDriverUtil.maximize();
        }
        LogHelper.getLogger().info("Scenario '" + scenario.getName() + "' STARTED");
    }

    @After
    public void tearDown(Scenario scenario) {
        LogHelper.getLogger().info("Scenario '" + scenario.getName() + "' " + scenario.getStatus().toString());
        WebDriverUtil.getDriver().manage().deleteAllCookies();
        try {
            WebDriverUtil.getDriver().quit();
            WebDriverUtil.setDriver(null);
        } catch (WebDriverException we) {
            LogHelper.getLogger().error("Unable to close browser");
        }
    }
}
