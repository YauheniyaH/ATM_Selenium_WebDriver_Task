package pageobject_model.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject_model.service.TestDataReader;

import static pageobject_model.service.TestDataReader.HOMEPAGE_URL;

public class CloudGoogleHomePage extends AbstractPage {

    @FindBy(xpath = "//span[contains(text(),'')]/ancestor::div[2]")
    private WebElement searchButton;
    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement searchTextBox;

    public CloudGoogleHomePage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(TestDataReader.getTestData(HOMEPAGE_URL));

    }

    public CloudGoogleSearchResultsPage searchForTerms(String term) {
        searchButton.click();
        searchTextBox.sendKeys(term);
        searchTextBox.sendKeys(Keys.ENTER);
        return new CloudGoogleSearchResultsPage(driver);
    }

}
