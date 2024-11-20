package pageobject_model.page.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MachineTypeDropdown extends DefaultDropDown {

    @FindBy(xpath = "//span[contains(text(),'Machine type')]/ancestor::div[2]")
    private WebElement machineTypeDropdown;

    public MachineTypeDropdown(WebDriver driver) {
        super(driver);
    }

    @Override
    public void expandDropdown() {
        machineTypeDropdown.click();
    }

    @Override
    public void selectDropDownValue(String dropdownValue) {
        machineTypeDropdown.findElement(By.xpath(String.format(PATH_DROPDOWN_TEMPLATE, dropdownValue))).click();
    }
}
