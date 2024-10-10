package pageobject_model.test;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.page.CloudGoogleComputeEnginePage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;


public class WebDriverSeleniumGridTest {
    private static final String GRID_HOST = "http://localhost:4444/wd/hub";

    private WebDriver driver;

    private CloudGoogleComputeEnginePage computeEnginePage;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() throws IOException, URISyntaxException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setPlatform(Platform.IOS);
        caps.setBrowserName("chrome");
        caps.setAcceptInsecureCerts(true);

        ChromeOptions options = new ChromeOptions();
        URL url = new URI(GRID_HOST).toURL();
        //  WebDriver driver = new RemoteWebDriver(url, caps);
        driver = new RemoteWebDriver(url, options); // why works with options, but not working with caps??
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        computeEnginePage = new CloudGoogleComputeEnginePage(driver);
        computeEnginePage.openPage();
    }

    @Test
    public void seleniumGridTest() {
        assertEquals("Compute Engine", computeEnginePage.pageTitle.getText());
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
