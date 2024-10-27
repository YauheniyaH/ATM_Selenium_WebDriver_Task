package pageobject_model.page.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import pageobject_model.service.TestDataReader;

import static pageobject_model.service.TestDataReader.WAIT_TIMEOUT_SECONDS;

public abstract class DefaultDropDown implements DropDown {
    protected static final String PATH_DROPDOWN_TEMPLATE = "//span[contains(text(),%s)]/ancestor::li[1]";

    protected WebDriver driver;

    protected WebElement dropDown;

    protected DefaultDropDown(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Integer.parseInt(TestDataReader.getTestData(WAIT_TIMEOUT_SECONDS))), this);
    }

    @Override
    public void expandDropdown() {
        this.dropDown.click();
    }

    @Override
    public void selectDropDownValue(String dropdownValue) {
        this.dropDown.findElement(By.xpath(String.format(PATH_DROPDOWN_TEMPLATE, dropdownValue))).click();

    }


}
