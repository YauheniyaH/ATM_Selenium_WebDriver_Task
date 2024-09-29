package pageobject_model.test;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.page.CloudGoogleComputeEnginePage;
import pageobject_model.page.CloudGoogleHomePage;

import java.io.IOException;


public class WebDriverCloudGoogleTest {
    private WebDriver driver;
    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--block-third-party-cookies");
        driver = new ChromeDriver( options.addArguments("--disable-search-engine-choice-screen"));
    }
    @Test(description = "Check that search results contains required link to calculator")
    public void searchResultsContainCalculator()  {
        WebElement searchResult= new CloudGoogleHomePage(driver)
                .openPage()
                .searchForTerms("Google Cloud Pricing Calculator")
                .findElementInSearchResult();
        Assert.assertNotNull("Search result is exists", searchResult);
        searchResult.click();
    }
   @Test(description = "Monthly rate for input parameters has correct value")
   public void calculateMonthlyRentForParameters() throws InterruptedException, IOException {
    CloudGoogleComputeEnginePage computeEnginePage= new CloudGoogleComputeEnginePage(driver).openPage()
        .inputValues();
    Thread.sleep(5000);
       String calculatedMonthRent= computeEnginePage
               .findMonthlyRent();
        Assert.assertTrue(calculatedMonthRent.equals("$3,384.50"));
   }
   @Test (description = "Check Input Values Correctness")
    public void calculatorValuesCheck() throws IOException {
       CloudGoogleComputeEnginePage computeEnginePage= new CloudGoogleComputeEnginePage(driver)
               .openPage()
               .inputValues();
      Assert.assertTrue( computeEnginePage.checkValues());
   }

    @AfterMethod(alwaysRun=true)
    public void browserTearDown(){
        driver.quit();
        driver=null;
    }
}
