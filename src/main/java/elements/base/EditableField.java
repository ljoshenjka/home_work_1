package elements.base;

import org.openqa.selenium.By;

abstract public class EditableField extends BaseField {
    public EditableField(BaseField parent, By locator) {
        super(parent, locator);
    }

    public EditableField(By locator) {
        super(locator);
    }
}
