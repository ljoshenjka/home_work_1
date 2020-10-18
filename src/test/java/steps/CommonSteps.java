package steps;

import base.BaseStep;
import io.cucumber.java.en.Given;
import pages.HomePage;

public class CommonSteps extends BaseStep {

    @Given("user opens Craigslist Finland website")
    public void userOpensCraigslistWebsite() {
        HomePage.openApp();
    }
}
