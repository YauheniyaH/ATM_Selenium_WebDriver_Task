package pageobject_model.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pageobject_model.service.TestDataReader;
import static pageobject_model.service.TestDataReader.BROWSER;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class DriverSingleton {
    private static WebDriver driver;
    private static final String GRID_HOST = "http://localhost:4444/wd/hub";

    private DriverSingleton() {
    }

    public static synchronized WebDriver getDriver() throws URISyntaxException, MalformedURLException {
        if (null == driver) {
            switch (TestDataReader.getTestData(BROWSER)) {
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                case "edge" -> {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                }
                case "chrome"-> {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
                // add case chrome
                default -> {
                    WebDriverManager.chromedriver().setup();
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setPlatform(Platform.MAC);
                    caps.setBrowserName("chrome");
                    caps.setAcceptInsecureCerts(true);
                    URL url = new URI(GRID_HOST).toURL();
                    driver = new RemoteWebDriver(url, caps);
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
