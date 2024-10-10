package pageobject_model.page;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import static java.lang.String.format;
import static java.util.logging.Level.INFO;
import static org.openqa.selenium.OutputType.FILE;


public class CloudGoogleComputeEnginePage extends AbstractPage {
    private static final String COMPUTE_ENGINE_ULR = "https://cloud.google.com/products/calculator?dl=CiQ1ZDg2ZjI0Yy1jMjNkLTQxZWYtOWFjNi0wZDFmMDcxMzdmN2YQCBokMUU1NEVBRjAtRUQ5QS00NUVFLUIzODItM0REQ0ZDNDAxQjYz";

    private static final String HIGHLIGHT_SCRIPT_TEMPLATE = "arguments[0].style.backgroundColor = '" + "%s" + "'";

    private static final int Y_OFFSET = 0;

    private static final Logger logger = Logger.getLogger(CloudGoogleComputeEnginePage.class.getName());

    @FindBy(xpath = "//input[@id='i6']")
    private WebElement numberOfInstances;

    @FindBy(xpath = "//*[contains(text(), 'Estimated cost')]/ancestor::div[1]//label")
    private WebElement monthRent;

    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement closeButtonOptional;

    @FindBy(xpath = "//span[contains(text(), 'Number of GPUs')]/ancestor::div[1]")
    private WebElement gpuNumberDropdown;

    @FindBy(xpath = "//li[@data-value='1']")
    private WebElement gpuNumberDropdownOption;

    @FindBy(xpath = "//button[@class='glue-cookie-notification-bar__accept']")
    private WebElement cookiesAcceptButton;

    @FindBy(xpath = "//div[contains(text(),'n1-standard-8')]")
    public WebElement instanceType;

    @FindBy(xpath = "//textarea[@aria-label='Rename Instances']")
    private WebElement instanceNameTextBox;

    @FindBy(xpath = "//input[@type='range' and @max='48']")
    private WebElement sliderNumberOfCPU;

    @FindBy(xpath = "//label[contains(text(), 'Regular')]/ancestor::div[1]")
    public WebElement modelRadioButton;

    @FindBy(xpath = "//button[@aria-label='Add GPUs']")
    public WebElement addGPURadioButton;

    @FindBy(xpath = "//input[@type='range' and @max='48']/ancestor::div[1]//span[contains(text(),'vCPUs')]")
    public WebElement labelNumberOfGPUs;

    @FindBy(xpath = "//label[contains(text(), '1 year')]/ancestor::div[1]")
    public WebElement committedTerm1YearButton;

    @FindBy(xpath = "//div[contains(text(), 'Estimated cost')]")
    public WebElement labelEstimatedCost;

    @FindBy(xpath = "//h1[@aria-label='Selected product title' ]")
    public WebElement pageTitle;

    public CloudGoogleComputeEnginePage(WebDriver driver) {
        super(driver);
    }

    public CloudGoogleComputeEnginePage openPage() {
        driver.get(COMPUTE_ENGINE_ULR);
        return this;
    }

    public void inputNumberOfInstances(String number) {
        numberOfInstances.clear();
        numberOfInstances.sendKeys(number);
    }

    public void selectDropdownValue(String dropdownName, String value) {
        DropDownObject dropdown = new DropDownObject(driver);
        dropdown.expandDropdown(dropdownName);
        dropdown.selectItemByValue(value);
    }

    public void optionalPopUpClose() {
        try {
            closeButtonOptional.click();
        } catch (Exception e) {
            logger.log(INFO, "no close button");
        }
    }

    public void inputGPUNumber(String value) {
        String template = "//li[@data-value='%s']";
        gpuNumberDropdown.click();
        gpuNumberDropdown.findElement(By.xpath(format(template, value))).click();
    }

    public void cookiesPopUpClose() {
        try {
            cookiesAcceptButton.click();
        } catch (Exception e) {
            logger.log(INFO, "no cookies");
        }
    }

    public String getMonthlyRent() {
        return monthRent.getText();
    }

    public void inputInstanceName(String instanceName) {
        Actions actions = new Actions(driver);
        actions.moveToElement(instanceNameTextBox).click();
        actions.sendKeys(instanceNameTextBox, instanceName).sendKeys(Keys.ENTER).perform();
    }

    public String getTexBoxInstanceName() {
        return instanceNameTextBox.getText();
    }

    public boolean checkControlValue(WebElement element, String value) {
        return element.getText().equals(value);
    }

    public void moveNumberOfCPUScrollBar(int shiftValue) {
        Actions actions = new Actions(driver);
        cookiesAcceptButton.click();
        closeButtonOptional.click();
        actions.moveToElement(sliderNumberOfCPU).clickAndHold().moveByOffset(shiftValue, Y_OFFSET).release().perform();
    }

    public void highlightElement(WebDriver driver, WebElement element) throws IOException {
        String bg = element.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript(format(HIGHLIGHT_SCRIPT_TEMPLATE, "yellow"), element);
        makeScreenshot();
        js.executeScript(format(HIGHLIGHT_SCRIPT_TEMPLATE, bg), element);
    }

    public void makeScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(FILE);
        FileUtils.copyFileToDirectory(screenshot, new File("src/test/test_output/screenshots"));
    }

    public void scrollToBottomOfPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

}
