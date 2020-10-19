package pages;

import custom_elements.SortingDropDown;
import elements.TextBox;
import org.openqa.selenium.By;
import utils.WebDriverUtil;

import java.util.ArrayList;
import java.util.List;

public class HousingPage {

    public static TextBox txbSearch = new TextBox(By.xpath("//form[@id='searchform']//input[@id='query']"));
    public static SortingDropDown drpSorting = new SortingDropDown(By.cssSelector(".search-sort .dropdown-list"));

    private static final String xptHousingItem = "//div[@id='sortable-results']//li[@class='result-row' and not(preceding-sibling::h4[@class='ban nearby'])]";

    public static List<HousingItem> getHousingItems() {
        int itemsNumber = WebDriverUtil.getElementsOrEmpty(By.xpath(xptHousingItem)).size();
        List<HousingItem> list = new ArrayList<>();
        for (int i = 1; i <= itemsNumber; i++) {
            list.add(getHousingItemByIndex(i));
        }
        return list;
    }

    public static HousingItem getHousingItemByIndex(int index) {
        return new HousingItem(By.xpath(String.format(xptHousingItem + "[%d]", index)));
    }
}
