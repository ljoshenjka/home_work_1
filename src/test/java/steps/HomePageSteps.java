package steps;

import base.BaseStep;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.App;

public class HomePageSteps extends BaseStep {

    @Then("checks that Home page is displayed")
    public void checksThatHomePageIsDisplayed() {
        Assert.assertTrue("Home page top banner is not displayed", App.lblTopBanner.isDisplayed());
    }

    @When("user change site language to {string}")
    public void userChangeSiteLanguageToEnglish(String language) {
        App.drpLanguage.setValue(language);
    }

    @Then("checks that Home page top banner header is {string}")
    public void checksThatHomePageTopBannerHeaderIsFinland(String text) {
        Assert.assertEquals("Wrong Home page top banner header", text, App.lblTopBanner.getValue());
    }

    @When("user navigates to Housing page")
    public void userNavigatesToHousingPage() {
        App.btnHousing.click();
    }
}
