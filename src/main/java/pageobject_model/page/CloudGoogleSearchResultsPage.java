package pageobject_model.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CloudGoogleSearchResultsPage extends AbstractPage{///  add extension from AbstractPage

    @FindBy(xpath = "//*[@data-ctorig='https://cloud.google.com/products/calculator']/ancestor::div[2]")
    private WebElement generalSearchResults;

    @Override
    protected AbstractPage openPage() {
        //method is not need for Search result page
        return null;
    }

    public CloudGoogleSearchResultsPage (WebDriver driver){
        super(driver);
    }
    public WebElement findElementInSearchResult (){
        WebElement searchItem = generalSearchResults;
       generalSearchResults.click();
        return searchItem;

    }
}
