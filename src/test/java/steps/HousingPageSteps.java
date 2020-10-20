package steps;

import base.BaseStep;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.HousingItem;
import pages.HousingPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HousingPageSteps extends BaseStep {

    @Then("checks that Housing page is opened")
    public void checksThatHousingPageIsOpened() {
        Assert.assertTrue("Housing page search box is not displayed", HousingPage.txbSearch.isDisplayed());
    }

    @Then("checks that Housing page sorting is set to {string}")
    public void checksThatHousingPageSortingIsSetTo(String sorting) {
        Assert.assertEquals("Wrong Housing page sorting selected", sorting, HousingPage.drpSorting.getValue());
    }

    @Then("checks that Housing page sorting options are available")
    public void checksThatHousingPageSortingOptionsAreAvailable(List<String> sortings) {
        Assert.assertEquals("Wrong sorting options", sortings, HousingPage.drpSorting.getAvailableOptions());
    }

    @When("user change Housing page sorting to {string}")
    public void userChangeHousingPageSortingTo(String sorting) {
        HousingPage.drpSorting.click();
        HousingPage.drpSorting.setValue(sorting);
    }

    @Then("^checks that Housing page results are sorted by price (ascending|descending)$")
    public void checksThatHousingPageResultsAreSortedByPrice(String sortingType) {
        ArrayList<String> obtainedList = new ArrayList<>();
        List<HousingItem> results = HousingPage.getHousingItems();
        for (HousingItem item : results) {
            obtainedList.add(item.lblPrice.getValue());
        }

        ArrayList<String> sortedList = new ArrayList<>(obtainedList);
        Collections.sort(sortedList);
        if (sortingType.equals("descending")) {
            Collections.reverse(sortedList);
        }

        Assert.assertEquals("Wrong Housing page result sorting", sortedList, obtainedList);
    }

    @When("user search for {string} on Housing page")
    public void userSearchForStringOnHousingPage(String search) {
        HousingPage.txbSearch.setValue(search);
        HousingPage.btnSearchSubmit.click();
    }
}
