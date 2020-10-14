package hook;

import constants.PropertyConfigs;
import helpers.LogHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriverException;
import utils.ConfigReader;
import utils.PropertiesWriter;
import utils.WebDriverSetup;
import utils.WebDriverUtil;

import java.net.MalformedURLException;

public class Hook {

    static {
        LogHelper.getLogger().info("URL: " + ConfigReader.getInstance().getValue(PropertyConfigs.APP_URL));
        PropertiesWriter writer = new PropertiesWriter(
                System.getProperty("user.dir") + "/target/", "buildInfo.properties");

        writer.setValue("URL", ConfigReader.getInstance().getValue(PropertyConfigs.APP_URL));
        writer.setValue("Browser", WebDriverUtil.driverType);
        writer.saveData();
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
        if (scenario.isFailed()) {
            byte[] screen = WebDriverUtil.getScreenshot();
            scenario.attach(screen, "image/png", "screenshot");
            String screenshotName = WebDriverUtil.writeScreenshotToFile(screen, scenario.getName().replaceAll(" ", "_"), null);
            LogHelper.getLogger().error("Scenario '" + scenario.getName() + "' FAILED, Screen shot: " + screenshotName);
        } else {
            LogHelper.getLogger().info("Scenario '" + scenario.getName() + "' PASSED");
        }
        WebDriverUtil.getDriver().manage().deleteAllCookies();
        try {
            WebDriverUtil.getDriver().quit();
            WebDriverUtil.setDriver(null);
        } catch (WebDriverException we) {
            LogHelper.getLogger().error("Unable to close browser");
        }
    }
}
