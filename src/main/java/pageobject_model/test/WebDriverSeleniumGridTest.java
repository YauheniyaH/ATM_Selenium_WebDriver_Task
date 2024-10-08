package pageobject_model.test;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import pageobject_model.page.CloudGoogleComputeEnginePage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class WebDriverSeleniumGridTest {

    @Test
    public void seleniumGridTest() throws URISyntaxException, IOException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setPlatform(Platform.IOS);
        caps.setBrowserName("chrome");
        caps.setAcceptInsecureCerts(true);
        ChromeOptions options = new ChromeOptions();
        String path = "http://localhost:4444/wd/hub";
        URL url = new URI(path).toURL();
//        WebDriver driver = new RemoteWebDriver(url, caps);
        WebDriver driver = new RemoteWebDriver(url, options); // why works with options, but not working with caps??
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        CloudGoogleComputeEnginePage computeEnginePage = new CloudGoogleComputeEnginePage(driver);
        computeEnginePage.openPage();


        driver.quit();
    }
}
