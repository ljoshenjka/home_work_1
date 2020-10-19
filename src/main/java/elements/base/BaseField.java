package elements.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WebDriverUtil;

import java.util.List;

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

    public Boolean isDisplayed() {
        List<WebElement> elements;
        if (getParent() != null) {
            if (getParent().isPresent()) {
                elements = WebDriverUtil.getElements(getParent().getWebElement(), locator);
            } else {
                return false;
            }
        } else {
            elements = WebDriverUtil.getElementsOrEmpty(locator);
        }
        return elements.size() != 0 && elements.get(0).isDisplayed();
    }

    public Boolean isPresent() {
        List<WebElement> elements;
        if (getParent() != null) {
            if (getParent().isPresent()) {
                elements = WebDriverUtil.getElementsOrEmpty(getParent().getWebElement(), locator);
            } else {
                return false;
            }
        } else {
            elements = WebDriverUtil.getElementsOrEmpty(locator);
        }
        return elements.size() > 0;
    }

    public String getValue() {
        return WebDriverUtil.getValue(getWebElement());
    }

    public void scrollToElement() {
        WebDriverUtil.scrollToElement(getWebElement());
    }
}
