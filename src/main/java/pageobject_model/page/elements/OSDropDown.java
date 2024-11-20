package pageobject_model.page.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OSDropDown extends DefaultDropDown{

    @FindBy(xpath = "//span[contains(text(),'Operating System / Software')]/ancestor::div[2]")
    private WebElement osDropdown;

    public OSDropDown(WebDriver driver) {
        super(driver);
    }

    @Override
    public void expandDropdown() {
        osDropdown.click();
    }

    @Override
    public void selectDropDownValue(String dropdownValue) {
        osDropdown.findElement(By.xpath(String.format(PATH_DROPDOWN_TEMPLATE, dropdownValue))).click();
    }
}
