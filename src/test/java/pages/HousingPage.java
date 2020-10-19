package pages;

import elements.TextBox;
import org.openqa.selenium.By;

public class HousingPage {

    public static TextBox txbSearch = new TextBox(By.xpath("//form[@id='searchform']//input[@id='query']"));
}
