package pages;

import elements.Label;
import elements.base.BaseField;
import elements.base.ClickableField;
import org.openqa.selenium.By;

public class HousingItem extends ClickableField {

    public Label lblDate = new Label(this, By.xpath(".//time"));
    public Label lblPrice = new Label(this, By.xpath(".//*[@class='result-price']"));
    public Label lblHood = new Label(this, By.xpath(".//*[@class='result-hood']"));
    public Label lblTitle = new Label(this, By.xpath(".//*[@class='result-title']"));

    public HousingItem(BaseField parent, By locator) {
        super(parent, locator);
    }

    public HousingItem(By locator) {
        super(locator);
    }
}
