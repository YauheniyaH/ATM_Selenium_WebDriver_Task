package pageobject_model.page.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GPUModelDropDown extends DefaultDropDown{
    @FindBy(xpath = "//span[contains(text(), 'GPU Model')]/ancestor::div[1]")
    private WebElement gpuModelDropdown;

    public GPUModelDropDown(WebDriver driver) {
        super(driver);
    }

    @Override
    public void expandDropdown() {
        gpuModelDropdown.click();
    }

    @Override
    public void selectDropDownValue(String dropdownValue) {
        gpuModelDropdown.findElement(By.xpath(String.format(PATH_DROPDOWN_TEMPLATE, dropdownValue))).click();
    }
}
