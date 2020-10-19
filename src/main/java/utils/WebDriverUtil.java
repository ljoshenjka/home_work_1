package utils;

import constants.PropertyConfigs;
import helpers.DateHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverUtil {
    private static final ThreadLocal<WebDriver> drivers = ThreadLocal.withInitial(() -> null);
    public static final String driverType = ConfigReader.getInstance().getValue(PropertyConfigs.SELENIUM_DRIVER);

    public static WebDriver getDriver() {
        return drivers.get();
    }

    public static void setDriver(WebDriver driver) {
        drivers.set(driver);
    }

    public static JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    public static WebElement getElement(By locator) {
        return getDriver().findElement(locator);
    }

    public static WebElement getElement(WebElement parent, By locator) {
        return parent.findElement(locator);
    }

    public static List<WebElement> getElements(By locator) {
        return getDriver().findElements(locator);
    }

    public static List<WebElement> getElements(WebElement parent, By locator) {
        return parent.findElements(locator);
    }

    public static String writeScreenshotToFile(byte[] screen, String nameTemplate, String path) {
        try {
            if (path == null) {
                path = "./target/screenshots/" + nameTemplate + "_" + DateHelper.getTodaysDateTime() + ".png";
            }
            FileUtils.writeByteArrayToFile(new File(path), screen);

        } catch (IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }

        return path;
    }

    public static byte[] getScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void maximize() {
        getDriver().manage().window().maximize();
    }

    public static void setImplicitWait() {
        getDriver().manage().timeouts().implicitlyWait(WaitUtil.implicitWait, TimeUnit.SECONDS);
    }

    public static List<WebElement> getElementsOrEmpty(WebElement parent, By locator, long timeout) {
        getDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        List<WebElement> temp = getElements(parent, locator);
        setImplicitWait();
        return temp;
    }

    public static List<WebElement> getElementsOrEmpty(WebElement parent, By locator) {
        return getElementsOrEmpty(parent, locator, 0);
    }

    public static List<WebElement> getElementsOrEmpty(By locator, long timeout) {
        getDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        List<WebElement> temp = getElements(locator);
        setImplicitWait();
        return temp;
    }

    public static List<WebElement> getElementsOrEmpty(By locator) {
        return getElementsOrEmpty(locator, 0);
    }

    public static void scrollToElement(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        getJsExecutor().executeScript(scrollElementIntoMiddle, element);
    }

    public static void click(WebElement element) {
        WebDriverUtil.scrollToElement(element);
        element.click();
        WaitUtil.waitForPageToLoad();
    }

    public static String getValue(WebElement element) {
        scrollToElement(element);
        return element.getText().trim();
    }
}
