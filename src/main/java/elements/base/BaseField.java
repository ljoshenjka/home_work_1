package elements.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WebDriverUtil;

abstract public class BaseField {
    private final By locator;
    private final BaseField parent;

    public BaseField(By locator) {
        this(null, locator);
    }

    public BaseField(BaseField parent, By locator) {
        this.parent = parent;
        this.locator = locator;
    }

    public By getLocator() {
        return locator;
    }

    public BaseField getParent() {
        return parent;
    }

    public WebElement getWebElement() {
        if (getParent() != null) {
            return WebDriverUtil.getElement(getParent().getWebElement(), locator);
        } else {
            return WebDriverUtil.getElement(locator);
        }
    }
}
