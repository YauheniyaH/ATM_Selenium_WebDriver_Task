package pageobject_model.page.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject_model.page.AbstractPage;

public class RadioButtonElement extends AbstractPage {
    // implement action for radio button
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
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        return null;
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
