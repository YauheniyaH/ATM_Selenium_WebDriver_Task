package pageobject_model.page;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;


public class CloudGoogleComputeEnginePage extends AbstractPage {
    private static final String COMPUTE_ENGINE_ULR = "https://cloud.google.com/products/calculator?dl=CiQ1ZDg2ZjI0Yy1jMjNkLTQxZWYtOWFjNi0wZDFmMDcxMzdmN2YQCBokMUU1NEVBRjAtRUQ5QS00NUVFLUIzODItM0REQ0ZDNDAxQjYz";
    private static final Logger logger = Logger.getLogger(CloudGoogleComputeEnginePage.class.getName());
    private final FileHandler fileLog = new FileHandler("ComputeEngineLog.log");
    @FindBy(xpath = "//input[@id='i6']")
    private WebElement numberOfInstances;
    @FindBy(xpath = "//*[contains(text(), 'Estimated cost')]/ancestor::div[1]//label")
    private WebElement monthRent;
    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement closeButtonOptional;
    @FindBy(xpath = "//label[contains(text(), 'Regular')]/ancestor::div[1]")
    private WebElement modelRadioButton;
    @FindBy(xpath = "//span[contains(text(),'n1-standard-8')]/ancestor::li[1]")
    private WebElement machineTypeDropdownOption;
    @FindBy(xpath = "//button[@aria-label='Add GPUs']")
    private WebElement addGPURadioButton;
    @FindBy(xpath = "//span[contains(text(),'NVIDIA TESLA P100')]/ancestor::li[1]")
    private WebElement gpuModelDropdownOption;
    @FindBy(xpath = "//span[contains(text(), 'Number of GPUs')]/ancestor::div[1]")
    private WebElement gpuNumberDropdown;
    @FindBy(xpath = "//li[@data-value='1']")
    private WebElement gpuNumberDropdownOption;
    @FindBy(xpath = "//label[contains(text(), '1 year')]/ancestor::div[1]")
    private WebElement committedTerm1YearButton;
    @FindBy(xpath = "//button[@class='glue-cookie-notification-bar__accept']")
    private WebElement cookiesAcceptButton;
    @FindBy(xpath = "//div[contains(text(),'n1-standard-8')]")
    private WebElement instanceType;
    @FindBy(xpath = "//span[contains(text(),'Region')]/ancestor::div[1]/span[2]")
    private WebElement region;
    @FindBy(xpath = "//textarea[@aria-label='Rename Instances']")
    private WebElement instanceNameTextbox;
    @FindBy(xpath = "//h1[@aria-label='Selected product title']")
    private WebElement selectedProductTitle;
    @FindBy(xpath = "//input[@type='range' and @max='48']")
    private WebElement sliderNumberOfCPU;
    @FindBy(xpath = "//input[@type='range' and @max='48']/ancestor::div[1]//span[contains(text(),'vCPUs')]")
    public WebElement labelNumberOfGPUs;
    @FindBy(xpath = "//div[contains(text(), 'Estimated cost')]")
    public WebElement labelEstimatedCost;
    private DropDownObject osDropdown = new DropDownObject(driver);
    private DropDownObject machineTypeDropdown = new DropDownObject(driver);
    private DropDownObject gpuModelDropdown = new DropDownObject(driver);


    public CloudGoogleComputeEnginePage(WebDriver driver) throws IOException {
        super(driver);

    }

    public CloudGoogleComputeEnginePage openPage() {
        driver.get(COMPUTE_ENGINE_ULR);
        return this;
    }

    public CloudGoogleComputeEnginePage inputValues() { //add parameters to method
        logger.addHandler(fileLog);


        //split method to each step on screen
        numberOfInstances.clear();
        numberOfInstances.sendKeys("4");
        //need to hide pop-ups which prevent continue of test
        try {
            closeButtonOptional.click();
        } catch (Exception e) {
            logger.log(Level.INFO, "no close button");
        }

        osDropdown.expandDropdown("OS");
        osDropdown.selectItemByValue("'Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)'");
        try {
            cookiesAcceptButton.click();
        } catch (Exception e) {
            logger.log(Level.INFO, "no cookies");
        }

        modelRadioButton.click();
        machineTypeDropdown.expandDropdown("machineType");
        machineTypeDropdown.selectItemByValue("'n1-standard-8'");
        addGPURadioButton.click();
        gpuModelDropdown.expandDropdown("gpuModel");
        gpuModelDropdown.selectItemByValue("'NVIDIA TESLA P100'");
        gpuNumberDropdown.click();
        gpuNumberDropdownOption.click();
        committedTerm1YearButton.click();
        return this;
    }

    public String getMonthlyRent() {
        return monthRent.getText();
    }

    public void inputInstanceName(String instanceName) {
        Actions actions = new Actions(driver);
        //add waiter, optional
        actions.moveToElement(instanceNameTextbox).click();
        actions.sendKeys(instanceNameTextbox, instanceName).sendKeys(Keys.ENTER).perform();
    }

    public String getTexBoxInstanceName (){
        return instanceNameTextbox.getText();
    }

    public boolean checkRadioButtonValue ( String value){
        return modelRadioButton.getText().equals(value);
    }

    public boolean checkInstanceTypeValue (String value){
        return instanceType.getText().equals(value);
    }

    public boolean checkCommittedTermValue(String value){
        return committedTerm1YearButton.getText().equals(value);
    }

    public void moveNumberOfCPUScrollBar(int shiftValue) {
        Actions actions = new Actions(driver);
        cookiesAcceptButton.click();
        closeButtonOptional.click();
        actions.moveToElement(sliderNumberOfCPU).clickAndHold().moveByOffset(shiftValue, 0).release().perform();
    }

    public void highlightElement(WebDriver driver, WebElement element) {
        String bg = element.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", element);
        makeScreenshot();
        js.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", element);

    }

    public void makeScreenshot() {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFileToDirectory(screenshot, new File("src/test/test_output/screenshots"));
          //  logger.htmlOutput("Taken screenshots <a href='screenshots/" + screenshot.getName() + "'>" + screenshot.getName() + "</a>");
        } catch (Exception e) {
          //  logger.error(e.getMessage());
        }
    }

    public void scrollToBottomOfPage (){
        JavascriptExecutor js =(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }



}
