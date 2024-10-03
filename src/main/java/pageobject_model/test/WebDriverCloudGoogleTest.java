package pageobject_model.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.page.CloudGoogleComputeEnginePage;


import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class WebDriverCloudGoogleTest {
    private WebDriver driver;
    private CloudGoogleComputeEnginePage computeEnginePage;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--block-third-party-cookies");
        driver = new ChromeDriver(options.addArguments("--disable-search-engine-choice-screen"));
       computeEnginePage= new CloudGoogleComputeEnginePage(driver);
        computeEnginePage.openPage();
    }


    @Test(description = "Monthly rate for input parameters has correct value")
    public void calculateMonthlyRentForParameters() throws InterruptedException {
        computeEnginePage.inputValues();
        Thread.sleep(5000);
        String calculatedMonthRent = computeEnginePage.getMonthlyRent();
        assertEquals("$3,384.50", calculatedMonthRent);
    }

    @Test(description = "Check Input Values Correctness")
    public void calculatorValuesCheck() {
        computeEnginePage.inputValues();
        WebElement modelRadioButton= driver.findElement(By.xpath("//label[contains(text(), 'Regular')]/ancestor::div[1]"));
        assertTrue(computeEnginePage.checkValues(modelRadioButton,"Regular"));
      WebElement instanceType=driver.findElement(By.xpath("//div[contains(text(),'n1-standard-8')]"));
      assertTrue(computeEnginePage.checkValues(instanceType,"n1-standard-8"));
    }


    @Test(description = "Click and hold Action test for Amount of memory scrollbar; check that set value by scrollbar equals value in text-box")
    public void numberOfCPUSliderCheck()  {
        WebElement labelNumberOfGPUs= driver.findElement(By.xpath("//input[@type='range' and @max='48']/ancestor::div[1]//span[contains(text(),'vCPUs')]"));

        computeEnginePage.moveNumberOfCPUScrollBar(50);
        AssertJUnit.assertEquals("16 vCPUs", labelNumberOfGPUs.getText());


    }

    @Test(description = "Instance name input check using Actions")
    public void inputTextBoxCheck() {
        String s = computeEnginePage.inputInstanceName();
        assertEquals("test instance", s);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
