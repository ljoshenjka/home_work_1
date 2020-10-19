package elements;

import elements.base.BaseField;
import elements.base.ClickableField;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.WaitUtil;
import utils.WebDriverUtil;

import java.util.List;

/**
 * DropDown
 */
public class DropDown extends ClickableField {

    public DropDown(BaseField parent, By locator) {
        super(parent, locator);
    }

    public DropDown(By locator) {
        super(locator);
    }

    public void setValue(String value) {
        List<WebElement> options = WebDriverUtil.getElements(getWebElement(), By.xpath(String.format(".//option[normalize-space()='%s']", value)));
        if (options.size() == 0) {
            Assert.fail("Value not found in selection, value: " + value);
        } else {
            options.get(0).click();
            WaitUtil.waitForPageToLoad();
        }
    }

    public void setValue(int index) {
        List<WebElement> options = WebDriverUtil.getElements(getWebElement(), By.tagName("option"));
        if (index > options.size()) {
            Assert.fail("Index too big for this selection, index: " + index);
        }
        options.get(index - 1).click();
        WaitUtil.waitForPageToLoad();
    }

    public String getValue() {
        Select select = new Select(getWebElement());
        return select.getFirstSelectedOption().getText().trim();
    }
}
