package pageobject_model.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


import static java.lang.String.format;

public class ComputeEngineEntity {
    private static final Logger logger = LogManager.getRootLogger();
    @FindBy(xpath = "//input[@id='i6']")
    private WebElement numberOfInstances;

    @FindBy(xpath = "//span[contains(text(), 'Number of GPUs')]/ancestor::div[1]")
    private WebElement gpuNumberDropdown;

    @FindBy(xpath = "//label[contains(text(), 'Regular')]/ancestor::div[1]")
    private WebElement modelButton;

    @FindBy(xpath = "//label[contains(text(), '1 year')]/ancestor::div[1]")
    private WebElement committedTerm1YearButton;

    public ComputeEngineEntity(WebDriver driver) {
        // added because do not understand why PageFactory was not initialized in AbstractPage
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public String getGpuNumberValue() {
        return gpuNumberDropdown.getText();
    }

    public WebElement getGpuNumberDropdown() {
        return gpuNumberDropdown;
    }

    public WebElement getModelRadioButton() {
        return modelButton;
    }

    public String getModelRadioButtonValue() {
        return modelButton.getText();
    }

    public void selectModelRadioButton() {
        logger.debug("Model was selected");
        this.modelButton.click();
    }

    public void setGpuNumber(String value) {
        String template = "//li[@data-value='%s']";
        gpuNumberDropdown.click();
        gpuNumberDropdown.findElement(By.xpath(format(template, value))).click();
        logger.debug("Number of GPUs was selected");
    }

    public WebElement getNumberOfInstances() {
        return numberOfInstances;
    }

    public String getNumberOfInstancesValue() {
        return numberOfInstances.getText();
    }

    public void setNumberOfInstances(String value) {
        numberOfInstances.clear();
        numberOfInstances.sendKeys(value);
        logger.debug("Number of instances was input into text-box");
    }

    public WebElement getCommittedTerm1YearButton() {
        return committedTerm1YearButton;
    }

    public void selectCommittedTerm1Year() {
        committedTerm1YearButton.click();
        logger.debug("Committed term was set to 1 year");
    }


}
