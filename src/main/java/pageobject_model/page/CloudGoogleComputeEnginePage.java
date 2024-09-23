package pageobject_model.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CloudGoogleComputeEnginePage {

    private WebDriver driver;
    private static final String COMPUTE_ENGINE_ULR="https://cloud.google.com/products/calculator?dl=CiQ1ZDg2ZjI0Yy1jMjNkLTQxZWYtOWFjNi0wZDFmMDcxMzdmN2YQCBokMUU1NEVBRjAtRUQ5QS00NUVFLUIzODItM0REQ0ZDNDAxQjYz";

    @FindBy(xpath = "//input[@id='i6']")
    private WebElement numberOfInsatances;
    @FindBy(xpath="//*[contains(text(), 'Estimated cost')]/ancestor::div[1]//label")
    private WebElement monthRent;

    @FindBy (xpath="//button[@aria-label='Close']")
    private WebElement closeButtonOptional;
    @FindBy(xpath = "//span[contains(text(),'Operating System / Software')]/ancestor::div[2]")
    private WebElement osDropdown;
    @FindBy (xpath="//span[contains(text(),'Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)')]/ancestor::li[1]")
    private WebElement osDropdownOption;
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


    public CloudGoogleComputeEnginePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public CloudGoogleComputeEnginePage openPage(){
        driver.get(COMPUTE_ENGINE_ULR);
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//             .until(CustomConditions.jQueryAJAXsCompleted());
        return this;
    }

    public CloudGoogleComputeEnginePage inpputValues(){
        numberOfInsatances.clear();
        numberOfInsatances.sendKeys("4");
        if(closeButtonOptional!=null){
            closeButtonOptional.click();
        }
        osDropdown.click();
        osDropdownOption.click();
        try {
            cookiesAcceptButton.click();
        }catch (Exception e){
            System.out.println(" no cookies");
        }
        modelRadioButton.click();
        machineTypeDropdown.click();
        machineTypeDropdownOption.click();
        addGPURadioButton.click();
        gpuModelDropdown.click();
        gpuModelDropdownOption.click();
        gpuNumberDropdown.click();
        gpuNumberDropdownOption.click();
        committedTerm1YearButton.click();
        return this;
    }

    public String findMonthlyRent(){
        long result;
        //System.out.println(monthRent.getText());
        return monthRent.getText();
    }

    public boolean checkValues(){
        boolean result =false;
        if(modelRadioButton.getText().equals("Regular") &&
                instanceType.getText().equals("n1-standard-8") &&
                committedTerm1YearButton.getText().equals("1 year")
        ){
            result=true;
        }


        return result;
    }

}
