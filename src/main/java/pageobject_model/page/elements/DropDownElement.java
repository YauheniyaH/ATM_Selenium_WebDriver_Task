package pageobject_model.page.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import pageobject_model.service.TestDataReader;

import static pageobject_model.service.TestDataReader.WAIT_TIMEOUT_SECONDS;

public class DropDownElement {

    private WebDriver driver;
    private static final String PATH_DROPDOWN_TEMPLATE = "//span[contains(text(),%s)]/ancestor::li[1]";

    @FindBy(xpath = "//span[contains(text(),'Operating System / Software')]/ancestor::div[2]")
    private WebElement osDropdown;
    @FindBy(xpath = "//span[contains(text(),'Machine type')]/ancestor::div[2]")
    private WebElement machineTypeDropdown;
    @FindBy(xpath = "//span[contains(text(), 'GPU Model')]/ancestor::div[1]")
    private WebElement gpuModelDropdown;

    public enum DropDownName {
        OS("OS"),
        MACHINE_TYPE("machineType"),
        GPU_MODEL("gpuModel");

        private String name;

        DropDownName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public DropDownElement(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Integer.parseInt(TestDataReader.getTestData(WAIT_TIMEOUT_SECONDS))), this);
    }

    public void expandDropdown(DropDownName dropDownName) {
        switch (dropDownName) {
            case OS -> osDropdown.click();
            case MACHINE_TYPE -> machineTypeDropdown.click();
            case GPU_MODEL -> gpuModelDropdown.click();
            default -> throw new IllegalStateException("Unexpected value: " + dropDownName);
        }
    }

    public void selectItemByValue(String dropdownValue) {
        osDropdown.findElement(By.xpath(String.format(PATH_DROPDOWN_TEMPLATE, dropdownValue))).click();
    }

}
