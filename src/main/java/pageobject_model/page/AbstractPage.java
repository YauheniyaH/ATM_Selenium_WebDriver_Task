package pageobject_model.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import pageobject_model.service.TestDataReader;

import static pageobject_model.service.TestDataReader.WAIT_TIMEOUT_SECONDS;

public abstract class AbstractPage {
    protected WebDriver driver;

    protected abstract void openPage();

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Integer.parseInt(TestDataReader.getTestData(WAIT_TIMEOUT_SECONDS))), this);
    }
}
