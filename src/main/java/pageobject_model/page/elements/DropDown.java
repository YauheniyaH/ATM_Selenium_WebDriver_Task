package pageobject_model.page.elements;

import org.openqa.selenium.WebElement;

public interface DropDown {
    public void expandDropdown();

    public void selectDropDownValue(String value);
}
