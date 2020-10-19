package steps;

import base.BaseStep;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.HousingPage;

public class HousingPageSteps extends BaseStep {

    @Then("checks that Housing page is opened")
    public void checksThatHousingPageIsOpened() {
        Assert.assertTrue("Housing page search box is not displayed", HousingPage.txbSearch.isDisplayed());
    }
}
