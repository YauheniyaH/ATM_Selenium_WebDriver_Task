package pageobject_model.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloudGoogleComputeEnginePage extends AbstractPage {
    private static final String COMPUTE_ENGINE_ULR="https://cloud.google.com/products/calculator?dl=CiQ1ZDg2ZjI0Yy1jMjNkLTQxZWYtOWFjNi0wZDFmMDcxMzdmN2YQCBokMUU1NEVBRjAtRUQ5QS00NUVFLUIzODItM0REQ0ZDNDAxQjYz";
    private static final Logger logger = Logger.getLogger(CloudGoogleComputeEnginePage.class.getName());
    private final FileHandler fileLog = new FileHandler("ComputeEngineLog.log");
    @FindBy(xpath = "//input[@id='i6']")
    private WebElement numberOfInstances;
    @FindBy(xpath="//*[contains(text(), 'Estimated cost')]/ancestor::div[1]//label")
    private WebElement monthRent;
    @FindBy (xpath="//button[@aria-label='Close']")
    private WebElement closeButtonOptional;
    @FindBy(xpath = "//label[contains(text(), 'Regular')]/ancestor::div[1]")
    private WebElement modelRadioButton;
    @FindBy (xpath = "//span[contains(text(),'Machine type')]/ancestor::div[2]")
    private WebElement machineTypeDropdown;
    @FindBy (xpath = "//span[contains(text(),'n1-standard-8')]/ancestor::li[1]")
    private WebElement machineTypeDropdownOption;
    @FindBy(xpath = "//button[@aria-label='Add GPUs']")
    private WebElement addGPURadioButton;
    @FindBy (xpath = "//span[contains(text(), 'GPU Model')]/ancestor::div[1]")
    private WebElement gpuModelDropdown;
    @FindBy (xpath = "//span[contains(text(),'NVIDIA TESLA P100')]/ancestor::li[1]")
    private WebElement gpuModelDropdownOption;
    @FindBy (xpath = "//span[contains(text(), 'Number of GPUs')]/ancestor::div[1]")
    private WebElement gpuNumberDropdown;
    @FindBy (xpath = "//li[@data-value='1']")
    private WebElement gpuNumberDropdownOption;
    @FindBy (xpath = "//label[contains(text(), '1 year')]/ancestor::div[1]")
    private WebElement committedTerm1YearButton;
    @FindBy(xpath = "//button[@class='glue-cookie-notification-bar__accept']")
    private WebElement cookiesAcceptButton;
    @FindBy(xpath = "//div[contains(text(),'n1-standard-8')]")
    private WebElement instanceType;
    @FindBy(xpath = "//span[contains(text(),'Region')]/ancestor::div[1]/span[2]")
    private WebElement region;


    public CloudGoogleComputeEnginePage(WebDriver driver) throws IOException {
        super(driver);
    }
    public CloudGoogleComputeEnginePage openPage(){
        driver.get(COMPUTE_ENGINE_ULR);
        return this;
    }
    public CloudGoogleComputeEnginePage inputValues() throws IOException {
        logger.addHandler(fileLog);
        DropDownObject osDropdown = new DropDownObject(driver);
        DropDownObject machineTypeDropdown=new DropDownObject(driver);
        DropDownObject gpuModelDropdown=new DropDownObject(driver);

        numberOfInstances.clear();
        numberOfInstances.sendKeys("4");
        try{
            closeButtonOptional.click();
        }catch (Exception e){
            logger.log(Level.INFO, "no close button");
            System.out.println("no close button");
        }
        osDropdown.expandDropdown("OS");
        osDropdown.selectItemByValue("'Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)'");
        try {
            cookiesAcceptButton.click();
        }catch (Exception e){
            logger.log(Level.INFO, "no cookies");
            System.out.println("no cookies");
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

    public String findMonthlyRent(){
        return monthRent.getText();
    }

    public boolean checkValues(){
        return modelRadioButton.getText().equals("Regular") &&
                instanceType.getText().equals("n1-standard-8") &&
                committedTerm1YearButton.getText().equals("1 year");
    }

}
