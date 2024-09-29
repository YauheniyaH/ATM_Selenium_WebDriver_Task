package pageobject_model.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;



public class CloudGoogleSearchResultsPage {
    private WebDriver driver;
    @FindBy(xpath = "//*[@data-ctorig='https://cloud.google.com/products/calculator']/ancestor::div[2]")
    private WebElement generalSearchResults;
    public CloudGoogleSearchResultsPage (WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }
    public WebElement findElementInSearchResult (){
        WebElement searchItem = generalSearchResults;
       generalSearchResults.click();
        return searchItem;

    }
}
