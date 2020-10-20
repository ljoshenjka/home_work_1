package custom_elements;

import elements.base.BaseField;
import elements.base.ClickableField;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WaitUtil;
import utils.WebDriverUtil;

import java.util.List;

public class SortingDropDown extends ClickableField {

    public SortingDropDown(BaseField parent, By locator) {
        super(parent, locator);
    }

    public SortingDropDown(By locator) {
        super(locator);
    }

    public void setValue(String value) {
        List<WebElement> options = WebDriverUtil.getElements(getWebElement(), By.xpath(String.format(".//li[normalize-space()='%s']", value)));
        if (options.size() == 0) {
            Assert.fail("Value not found in selection, value: " + value);
        } else {
            options.get(0).click();
            WaitUtil.waitForPageToLoad();
        }
    }

    public void setValue(int index) {
        List<WebElement> options = WebDriverUtil.getElements(getWebElement(), By.tagName("li"));
        if (index > options.size()) {
            Assert.fail("Index too big for this selection, index: " + index);
        }
        options.get(index - 1).click();
        WaitUtil.waitForPageToLoad();
    }

    public List<String> getAvailableOptions() {
        List<WebElement> options = WebDriverUtil.getElements(getWebElement(), By.tagName("li"));
        return WebDriverUtil.getStringListFromWebElementsList(options);
    }
}
