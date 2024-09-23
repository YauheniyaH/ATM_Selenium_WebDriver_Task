package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;

public class WebDriverSeleniumCloudGoogleWithoutPageObjectTest {


    private WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--block-third-party-cookies");
        driver = new ChromeDriver( options.addArguments("--disable-search-engine-choice-screen"));
        //driver.manage().window().maximize();


    }

    @Test(description = "Just first test")
    public void commonSearchTermResultsAreNotEmpty() throws InterruptedException {
        driver.get("https://cloud.google.com/");

        String specChar= String.format("%c", '\ue8b6');
        WebElement searchButton = waitForElementLocatedBy(driver, By.xpath("//span[contains(text(),'')]/ancestor::div[2]"));
        searchButton.click();
        WebElement searchTextBox =waitForElementLocatedBy(driver, By.xpath("//input[@aria-label='Search']"));
        searchTextBox.sendKeys("Google Cloud Platform Pricing Calculator");
        searchTextBox.sendKeys(Keys.ENTER);
        WebElement link = waitForElementLocatedBy(driver, By.xpath("//*[contains(text(),'Google Cloud Pricing Calculator')]"));
        link.click();

        WebElement estimateButton = waitForElementLocatedBy(driver, By.xpath("//span[contains(text(), 'Add to estimate')]/ancestor::button[1]"));
       estimateButton.click();

        WebElement computeEngineTab= waitForElementLocatedBy(driver, By.xpath("//h2[contains(text(),'Compute Engine')]/ancestor::div[4]"));
        Wait<WebDriver> wait = new FluentWait<>(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Compute Engine')]/ancestor::div[4]"))).click();
        //computeEngineTab.click();


        WebElement numberOfInstances = waitForElementLocatedBy(driver, By.xpath("//input[@id='c22']"));
        numberOfInstances.clear();
       numberOfInstances.sendKeys("4");

      //  driver.get("https://cloud.google.com/products/calculator?dl=CiQzYzFmMjE5Yy05N2Y1LTQxNGMtYWZiNC04YjI5MGFlMzBlODAQCBokMDBEMTMzREUtMzZERi00MjRCLUEyOUItQjhDREU2OEE4REJC");
//Operating System
        WebElement osDropDown = waitForElementLocatedBy(driver, By.xpath("//span[contains(text(),'Operating System / Software')]/ancestor::div[2]"));
        osDropDown.click();
        WebElement osDropDownOption = waitForElementLocatedBy(driver, By.xpath("//span[contains(text(),'Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)')]/ancestor::li[1]"));
        osDropDownOption.click();
//Cookies
        try {
            WebElement cookiesAccept = waitForElementLocatedBy(driver, By.xpath("//button[@class='glue-cookie-notification-bar__accept']"));
            cookiesAccept.click();
        }catch (Exception e){
            System.out.println(" no cookies");
        }

//Model
        WebElement modelRadioButton = waitForElementLocatedBy(driver, By.xpath("//label[contains(text(), 'Regular')]/ancestor::div[1]"));
        modelRadioButton.click();
//Machine type
        WebElement machineTypeDropdown = waitForElementLocatedBy(driver, By.xpath("//span[contains(text(),'Machine type')]/ancestor::div[2]"));
        machineTypeDropdown.click();

        WebElement machineTypeDropdownOption = waitForElementLocatedBy(driver, By.xpath("//span[contains(text(),'n1-standard-8')]/ancestor::li[1]"));
        machineTypeDropdownOption.click();

//Add GPU
        WebElement addGPURadioButton = waitForElementLocatedBy(driver, By.xpath("//button[@aria-label='Add GPUs']"));
        addGPURadioButton.click();

//GPU Model
        WebElement gpuModelDropdown = waitForElementLocatedBy(driver, By.xpath("//span[contains(text(), 'GPU Model')]/ancestor::div[1]"));
        gpuModelDropdown.click();
        WebElement gpuModelDropdownOption = waitForElementLocatedBy(driver, By.xpath("//span[contains(text(),'NVIDIA TESLA P100')]/ancestor::li[1]"));
        gpuModelDropdownOption.click();

//Number of GPUs
        WebElement gpuNumberDropdown = waitForElementLocatedBy(driver, By.xpath("//span[contains(text(), 'Number of GPUs')]/ancestor::div[1]"));
        gpuNumberDropdown.click();
        WebElement gpuNumberDropdownOption = waitForElementLocatedBy(driver, By.xpath("//li[@data-value='1']"));
        gpuNumberDropdownOption.click();

//Committed usage
        WebElement committedTerm= waitForElementLocatedBy(driver, By.xpath("//label[contains(text(), '1 year')]/ancestor::div[1]"));
        committedTerm.click();

      //  WebElement checkUpdates = waitForElementLocatedBy(driver, By.xpath("//span[contains(text(),'Service cost updated')]"));



//Estimated cost
        WebElement estimatedRent =waitForElementLocatedBy(driver, By.xpath("//*[contains(text(), 'Estimated cost')]/ancestor::div[1]//label"));
     //   Thread.sleep(5000);
    //    wait.until(ExpectedConditions.;
        System.out.println("Estimated cost " +estimatedRent.getText());









    }


    private static WebElement waitForElementLocatedBy (WebDriver driver, By by){
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
