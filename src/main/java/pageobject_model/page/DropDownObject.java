package pageobject_model.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DropDownObject extends AbstractPage {
    private static final String PATH_DROPDOWN_TEMPLATE = "//span[contains(text(),%s)]/ancestor::li[1]";
    @FindBy(xpath = "//span[contains(text(),'Operating System / Software')]/ancestor::div[2]")
    private WebElement osDropdown;
    @FindBy (xpath = "//span[contains(text(),'Machine type')]/ancestor::div[2]")
    private WebElement machineTypeDropdown;
    @FindBy (xpath = "//span[contains(text(), 'GPU Model')]/ancestor::div[1]")
    private WebElement gpuModelDropdown;

    public DropDownObject(WebDriver driver){
        super(driver);
    }
    public void expandDropdown(String dropDownName){
        switch (dropDownName) {
            case "OS" -> osDropdown.click();
            case "machineType" -> machineTypeDropdown.click();
            case "gpuModel" -> gpuModelDropdown.click();
        }
    }
    public void selectItemByValue(String dropdownValue){
        osDropdown.findElement(By.xpath(String.format(PATH_DROPDOWN_TEMPLATE, dropdownValue))).click();
    }
    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
