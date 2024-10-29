package pageobject_model.test;

import org.junit.Assert;

import org.testng.annotations.Test;
import pageobject_model.page.CloudGoogleHomePage;

public class WebDriverCloudGoogleSearchTest extends CommonConditions {

    @Test(description = "Check that search results contains required link to calculator")
    public void searchResultsContainCalculator() {
        CloudGoogleHomePage searchResult = new CloudGoogleHomePage(driver);
        searchResult.openPage();
        searchResult.searchForTerms("Google Cloud Pricing Calculator")
                .findElementInSearchResult();
        Assert.assertNotNull("Search result exists", searchResult);
    }
}
