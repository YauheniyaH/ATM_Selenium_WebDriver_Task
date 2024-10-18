package pageobject_model.test;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.page.CloudGoogleComputeEnginePage;
import pageobject_model.page.DropDownObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;



public class WebDriverCloudGoogleTest {
    private static final String GRID_HOST = "http://localhost:4444/wd/hub";
    private WebDriver driver;
    private CloudGoogleComputeEnginePage computeEnginePage;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() throws URISyntaxException, MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setPlatform(Platform.MAC);
        caps.setBrowserName("chrome");
        caps.setAcceptInsecureCerts(true);
        URL url = new URI(GRID_HOST).toURL();
        driver = new RemoteWebDriver(url, caps);
        // find parameter about clean cache
        computeEnginePage = new CloudGoogleComputeEnginePage(driver);
        computeEnginePage.openPage();
    }


    @Test(description = "Monthly rate for input parameters has correct value")
    public void calculateMonthlyRentForParameters() throws InterruptedException {
        computeEnginePage.inputNumberOfInstances("4");
        computeEnginePage.optionalPopUpClose();

        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.OS,"'Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)'");
        computeEnginePage.cookiesPopUpClose();

        computeEnginePage.modelRadioButton.click();
        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.MACHINE_TYPE,"'n1-standard-8'");

        computeEnginePage.addGPURadioButton.click();
        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.GPU_MODEL,"'NVIDIA TESLA P100'");
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

        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.OS,"'Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)'");
        computeEnginePage.cookiesPopUpClose();

        computeEnginePage.modelRadioButton.click();
        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.MACHINE_TYPE,"'n1-standard-8'");

        computeEnginePage.addGPURadioButton.click();
        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.GPU_MODEL,"'NVIDIA TESLA P100'");
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
