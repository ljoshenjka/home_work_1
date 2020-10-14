package utils;

import constants.PropertyConfigs;
import helpers.LogHelper;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

    public static final int implicitWait = Integer.parseInt(ConfigReader.getInstance().getValue(PropertyConfigs.SELENIUM_IMPLICITLY_WAIT));
    private static final int waitForAction = Integer.parseInt(ConfigReader.getInstance().getValue(PropertyConfigs.APP_WAIT_FOR_ACTION));
    private static final int timeout = Integer.parseInt(ConfigReader.getInstance().getValue(PropertyConfigs.APP_WAIT));

    public static void waitForAction(ExpectedCondition<Boolean> condition, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverUtil.getDriver(), timeoutInSeconds);
        wait.ignoring(WebDriverException.class).until(condition);
    }

    public static void waitForActionTimeout(ExpectedCondition<Boolean> condition) {
        waitForAction(condition, timeout);
    }

    public static void waitForPageToLoad() {
        // wait for jQuery requests to finish
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long) WebDriverUtil.getJsExecutor().executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                // no jQuery present
                return true;
            }
        };
        // wait for Page to load
        ExpectedCondition<Boolean> jsLoad = driver -> {
            try {
                return WebDriverUtil.getJsExecutor().executeScript("return document.readyState").toString().equals("complete");
            } catch (Exception e) {
                // shit happened
                LogHelper.getLogger().error("Some error in Wait for Page to load JS: " + e.getMessage());
                return false;
            }
        };
        // wait for window onload
        ExpectedCondition<Boolean> windowLoad = driver -> {
            try {
                return Boolean.parseBoolean(WebDriverUtil.getJsExecutor().executeScript("return iWOLoaded").toString());
            } catch (Exception e) {
                LogHelper.getLogger().error("iWOLoaded error: " + e.getMessage());
                return false;
            }
        };
        waitForActionTimeout(jsLoad);
        waitForActionTimeout(jQueryLoad);
    }
}
