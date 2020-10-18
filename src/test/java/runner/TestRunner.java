package runner;

import helpers.LogHelper;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import utils.WebDriverUtil;

/**
 * Cucumber test runner
 */
@RunWith(Cucumber.class) //JUnit
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"steps", "hook"},
        tags = "@testSuite",
        plugin = {"rerun:target/rerun.txt", "json:target/reports/cucumber.json"}
)

public class TestRunner {
    @AfterClass
    public static void afterClass() {
        if (WebDriverUtil.getDriver() != null) {
            LogHelper.getLogger().info("Close browser in afterClass");
            WebDriverUtil.getDriver().quit();
            WebDriverUtil.setDriver(null);
        }
    }
}
