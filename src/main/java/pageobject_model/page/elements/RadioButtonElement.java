package pageobject_model.page.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import pageobject_model.page.AbstractPage;
import pageobject_model.service.TestDataReader;

import static pageobject_model.service.TestDataReader.WAIT_TIMEOUT_SECONDS;

public class RadioButtonElement {
    private final WebDriver driver;
    @FindBy(xpath = "//button[@aria-label='Add GPUs']")
    private WebElement addGPURadioButton;

    public enum RadioButtonName {
        ADD_GPU("addGPU");

        private String name;

        RadioButtonName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public void switchRadioButton(RadioButtonName radioButtonName) {
        switch (radioButtonName) {
            case ADD_GPU -> addGPURadioButton.click();
            default -> throw new IllegalStateException("Unexpected value: " + radioButtonName);
        }
    }

    public RadioButtonElement(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Integer.parseInt(TestDataReader.getTestData(WAIT_TIMEOUT_SECONDS))), this);

    }

    public boolean checkRadioButtonEnabled(RadioButtonName radioButtonName) {
        switch (radioButtonName) {
            case ADD_GPU -> {
                return addGPURadioButton.getAttribute("aria-checked").equals("true");
            }
            default -> throw new IllegalStateException("Unexpected value: " + radioButtonName);
        }
    }

}
