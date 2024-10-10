package pageobject_model.test;

import org.openqa.selenium.WebDriver;
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
        // find parameter about clean cache
        computeEnginePage = new CloudGoogleComputeEnginePage(driver);
        computeEnginePage.openPage();
    }


    @Test(description = "Monthly rate for input parameters has correct value")
    public void calculateMonthlyRentForParameters() throws InterruptedException {
        computeEnginePage.inputNumberOfInstances("4");
        computeEnginePage.optionalPopUpClose();

        computeEnginePage.selectDropdownValue("OS","'Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)'");
        computeEnginePage.cookiesPopUpClose();

        computeEnginePage.modelRadioButton.click();
        computeEnginePage.selectDropdownValue("machineType","'n1-standard-8'"); //dropdownName move to enum

        computeEnginePage.addGPURadioButton.click();
        computeEnginePage.selectDropdownValue("gpuModel","'NVIDIA TESLA P100'");
        computeEnginePage.inputGPUNumber("1");

        computeEnginePage.committedTerm1YearButton.click();

        Thread.sleep(5000);
        String calculatedMonthRent = computeEnginePage.getMonthlyRent();
        assertEquals("$3,384.50", calculatedMonthRent);
    }

    @Test(description = "Check Input Values Correctness")
    public void calculatorValuesCheck() {
        computeEnginePage.inputNumberOfInstances("4");
        computeEnginePage.optionalPopUpClose();

        computeEnginePage.selectDropdownValue("OS","'Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)'");
        computeEnginePage.cookiesPopUpClose();

        computeEnginePage.modelRadioButton.click();
        computeEnginePage.selectDropdownValue("machineType","'n1-standard-8'");

        computeEnginePage.addGPURadioButton.click();
        computeEnginePage.selectDropdownValue("gpuModel","'NVIDIA TESLA P100'");
        computeEnginePage.inputGPUNumber("1");

        computeEnginePage.committedTerm1YearButton.click();

        assertTrue(computeEnginePage.checkControlValue(computeEnginePage.modelRadioButton,"Regular"));
        assertTrue(computeEnginePage.checkControlValue(computeEnginePage.instanceType, "n1-standard-8"));
        assertTrue(computeEnginePage.checkControlValue(computeEnginePage.committedTerm1YearButton,"1 year"));
    }

    @Test(description = "Click and hold Action test for Amount of memory scrollbar; check that set value by scrollbar equals value in text-box")
    public void numberOfCPUSliderCheck() throws IOException {
        computeEnginePage.moveNumberOfCPUScrollBar(50);
        computeEnginePage.highlightElement(driver, computeEnginePage.labelNumberOfGPUs);
        AssertJUnit.assertEquals("16 vCPUs", computeEnginePage.labelNumberOfGPUs.getText());
    }

    @Test(description = "Instance name input check using Actions")
    public void inputTextBoxCheck() {
        String inputValue = "test instance";
        computeEnginePage.inputInstanceName(inputValue);
        assertEquals(inputValue, computeEnginePage.getTexBoxInstanceName());
    }

    @Test(description = "Check that Estimated cost stay visible if scroll to the bottom of the page")
    public void labelEstimatedCostVisibilityCheck() {
        computeEnginePage.scrollToBottomOfPage();
        assertTrue(computeEnginePage.labelEstimatedCost.isDisplayed());
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
