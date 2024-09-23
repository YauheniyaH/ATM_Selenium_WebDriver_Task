package pageobject_model.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomConditions {
    public static ExpectedCondition<Boolean> jQueryAJAXsCompleted(){
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor)driver).executeScript("return(window.jQuery!=null) && (JQUESRY.ACTIVE== = 0);");
            }
        };
    }

    public static WebElement waitForElementLocatedBy (WebDriver driver, By by){
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
