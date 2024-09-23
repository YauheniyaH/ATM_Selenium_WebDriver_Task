package pageobject_model.test;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.page.CloudGoogleComputeEnginePage;
import pageobject_model.page.CloudGoogleHomePage;


public class WebDriverCloudGoogleTest {


    private WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--block-third-party-cookies");
        driver = new ChromeDriver( options.addArguments("--disable-search-engine-choice-screen"));
       // ? driver.manage().window().maximize();
    }

    @Test(description = "Just first test")
    public void commonSearchTermResultsAreNotEmpty() throws InterruptedException {
        WebElement searchResult= new CloudGoogleHomePage(driver)
                .openPage()
                .searchForTerms("Google Cloud Pricing Calculator")
                .findElementInSearchResult();

        Assert.assertNotNull("Search result is exists", searchResult);
        searchResult.click();



    }



   @Test(description = "Calculate monthly rent for Compute Engine")
   public void calculateMonthlyRent() throws InterruptedException {

    CloudGoogleComputeEnginePage computeEnginePage= new CloudGoogleComputeEnginePage(driver).openPage()
        .inpputValues();
    Thread.sleep(5000);
       String calculatedMonthRent= computeEnginePage
               .findMonthlyRent();

        Assert.assertTrue(calculatedMonthRent.equals("$3,384.50"));
   }


   @Test (description = "Check Input Values")
    public void calculatorValuesCheck(){
       CloudGoogleComputeEnginePage computeEnginePage= new CloudGoogleComputeEnginePage(driver)
               .openPage()
               .inpputValues();

      Assert.assertTrue( computeEnginePage.checkValues());
   }

//    @AfterMethod(alwaysRun=true)
//    public void browserTearDown(){
//        driver.quit();
//        driver=null;
//    }


}
