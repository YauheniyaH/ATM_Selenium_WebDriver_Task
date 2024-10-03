package pageobject_model.test;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.page.CloudGoogleHomePage;

public class WebDriverCloudGoogleSearchTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--block-third-party-cookies");
        driver = new ChromeDriver(options.addArguments("--disable-search-engine-choice-screen"));
    }

    @Test(description = "Check that search results contains required link to calculator")
    public void searchResultsContainCalculator() {
        WebElement searchResult = new CloudGoogleHomePage(driver)
                .openPage()
                .searchForTerms("Google Cloud Pricing Calculator")
                .findElementInSearchResult();
        Assert.assertNotNull("Search result is exists", searchResult);
        searchResult.click();
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
