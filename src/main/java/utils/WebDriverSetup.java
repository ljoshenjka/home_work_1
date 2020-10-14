package utils;

import constants.PropertyConfigs;
import exceptions.TestException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class WebDriverSetup {

    public static WebDriver setupWebDriver(String driverType) throws MalformedURLException {
        WebDriver driver;
        switch (driverType) {
            case PropertyConfigs.FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(getFirefoxOptions());
                break;
            }
            case PropertyConfigs.CHROME: {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(getChromeOptions());
                break;
            }
            case PropertyConfigs.EDGE: {
                driver = new EdgeDriver();
                break;
            }
            case PropertyConfigs.IE: {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            }
            default:
                throw new TestException("Invalid driver type: " + driverType);
        }
        return driver;
    }

    private static ChromeOptions getChromeOptions() {
        return new ChromeOptions();
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        return firefoxOptions;
    }
}
