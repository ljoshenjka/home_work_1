package elements.base;

import org.openqa.selenium.By;

abstract public class ClickableField extends BaseField {
    public ClickableField(BaseField parent, By locator) {
        super(parent, locator);
    }

    public ClickableField(By locator) {
        super(locator);
    }
}
