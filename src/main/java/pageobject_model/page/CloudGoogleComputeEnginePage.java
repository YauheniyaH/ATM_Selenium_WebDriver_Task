package pageobject_model.page;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import pageobject_model.model.ComputeEngineEntity;
import pageobject_model.page.elements.DropDownElement;
import pageobject_model.page.elements.RadioButtonElement;
import pageobject_model.service.TestDataReader;

import static java.lang.String.format;
import static org.openqa.selenium.OutputType.FILE;
import static pageobject_model.service.TestDataReader.COMPUTE_ENGINE_ULR;


public class CloudGoogleComputeEnginePage extends AbstractPage {
    private static final String HIGHLIGHT_SCRIPT_TEMPLATE = "arguments[0].style.backgroundColor = '" + "%s" + "'";

    private static final int Y_OFFSET = 0;

    private static final Logger logger = LogManager.getRootLogger();

    private ComputeEngineEntity computeEngineEntity;

    @FindBy(xpath = "//*[contains(text(), 'Estimated cost')]/ancestor::div[1]//label")
    private WebElement monthRent;

    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement closeButtonOptional;

    @FindBy(xpath = "//button[@class='glue-cookie-notification-bar__accept']")
    private WebElement cookiesAcceptButton;

    @FindBy(xpath = "//div[contains(text(),'n1-standard-8')]")
    public WebElement machineTypeSelected;

    @FindBy(xpath = "//textarea[@aria-label='Rename Instances']")
    private WebElement instanceNameTextBox;

    @FindBy(xpath = "//input[@type='range' and @max='48']")
    private WebElement sliderNumberOfCPU;

    @FindBy(xpath = "//input[@type='range' and @max='48']/ancestor::div[1]//span[contains(text(),'vCPUs')]")
    public WebElement labelNumberOfGPUs;

    @FindBy(xpath = "//div[contains(text(), 'Estimated cost')]")
    public WebElement labelEstimatedCost;

    @FindBy(xpath = "//h1[@aria-label='Selected product title' ]")
    public WebElement pageTitle;

    public CloudGoogleComputeEnginePage(WebDriver driver) {
        super(driver);
        computeEngineEntity = new ComputeEngineEntity(driver);
    }

    public void openPage() {
        driver.get(TestDataReader.getTestData(COMPUTE_ENGINE_ULR));
        logger.info("ComputeEnginePage is open");
    }

    public ComputeEngineEntity getComputeEngineEntity() {
        return computeEngineEntity;
    }

    public void selectDropdownValue(DropDownElement.DropDownName dropdownName, String value) {
        DropDownElement dropdown = new DropDownElement(driver);
        dropdown.expandDropdown(dropdownName);
        dropdown.selectItemByValue(value);
        logger.info(String.format("Input dropdown value for %s", dropdownName));
    }

    public void enableRadioButton (RadioButtonElement.RadioButtonName radioButtonName){
        RadioButtonElement radioButton = new RadioButtonElement(driver);
        if(!radioButton.checkRadioButtonEnabled(radioButtonName)){
            radioButton.switchRadioButton(radioButtonName);
            logger.debug("Add GPUs option was enabled");
        }
    }

    public void optionalPopUpClose() {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", closeButtonOptional);
            logger.info("Sharing link pop-up was closed");
        } catch (Exception e) {
            logger.debug("no sharing link pop-up displayed");
        }
    }

    public void cookiesPopUpClose() {
        try {
            cookiesAcceptButton.click();
            logger.info("Cookies pop-up was closed");
        } catch (Exception e) {
            logger.warn("no cookies pop-up displayed");
        }
    }

    public String getMonthlyRent() {
        logger.info("Read month rent value");
        return monthRent.getText();
    }

    public void inputInstanceName(String instanceName) {
        Actions actions = new Actions(driver);
        actions.moveToElement(instanceNameTextBox).click();
        actions.sendKeys(instanceNameTextBox, instanceName).sendKeys(Keys.ENTER).perform();
        logger.debug("Instance name was updated");// add value and locator to log
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
        logger.debug(String.format("Number of CPUs scroll bar was moved by %s to the right", shiftValue));
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
