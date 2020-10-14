package elements.base;

import org.openqa.selenium.By;

abstract public class StaticField extends BaseField {
    public StaticField(BaseField parent, By locator) {
        super(parent, locator);
    }

    public StaticField(By locator) {
        super(locator);
    }
}
