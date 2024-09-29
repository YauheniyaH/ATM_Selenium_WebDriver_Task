package pageobject_model.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DropDownObject extends AbstractPage {
    @FindBy(xpath = "//span[contains(text(),'Operating System / Software')]/ancestor::div[2]")
    private WebElement osDropdown;
    @FindBy (xpath = "//span[contains(text(),'Machine type')]/ancestor::div[2]")
    private WebElement machineTypeDropdown;
    @FindBy (xpath = "//span[contains(text(), 'GPU Model')]/ancestor::div[1]")
    private WebElement gpuModelDropdown;
    private WebDriver driver;
    public DropDownObject(WebDriver driver){
        super(driver);
    }
    public void expandDropdown(String dropDownName){
        switch (dropDownName){
            case "OS":{
                osDropdown.click();
                break;
            }
            case "machineType":{
                machineTypeDropdown.click();
                break;
            }
            case "gpuModel":{
                gpuModelDropdown.click();
                break;
            }
        }
    }
    public void selectItemByValue(String dropdownValue){
        String pathDropdownTemplate = "%s%s)]/ancestor::li[1]";
        String dropDownValueStartPart = "//span[contains(text(),";
        osDropdown.findElement(By.xpath(String.format(pathDropdownTemplate, dropDownValueStartPart,dropdownValue,")]/ancestor::li[1]"))).click();
    }
    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
