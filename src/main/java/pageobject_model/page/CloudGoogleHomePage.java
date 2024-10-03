package pageobject_model.page;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CloudGoogleHomePage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    @FindBy(xpath = "//span[contains(text(),'')]/ancestor::div[2]")
    private WebElement searchButton;
    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement searchTextBox;

    public CloudGoogleHomePage(WebDriver driver) {
        super(driver);
    }

    public CloudGoogleHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public CloudGoogleSearchResultsPage searchForTerms(String term) {
        searchButton.click();
        searchTextBox.sendKeys(term);
        searchTextBox.sendKeys(Keys.ENTER);
        return new CloudGoogleSearchResultsPage(driver);
    }

}
