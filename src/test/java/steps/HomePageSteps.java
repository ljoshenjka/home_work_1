package steps;

import base.BaseStep;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import utils.WebDriverUtil;

public class HomePageSteps extends BaseStep {

    @Then("checks that Home page is displayed")
    public void checksThatHomePageIsDisplayed() {
        WebDriverUtil.getElement(By.xpath("//div[@id='failingId']")).click();
    }
}
