package elements.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utils.WaitUtil;

abstract public class EditableField extends BaseField {
    public EditableField(BaseField parent, By locator) {
        super(parent, locator);
    }

    public EditableField(By locator) {
        super(locator);
    }

    public void setValue(String value) {
        WebElement element = getWebElement();
        element.clear();
        element.sendKeys(value);
        element.sendKeys(Keys.TAB);
        WaitUtil.waitForPageToLoad();
    }
}
