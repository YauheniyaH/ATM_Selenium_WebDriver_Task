package pageobject_model.util;

import com.epam.reportportal.service.ReportPortal;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pageobject_model.driver.DriverSingleton;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class TestListener implements ITestListener   {

    private final Logger log = LogManager.getRootLogger();
    private static final Logger LOGGER = LogManager.getLogger(TestListener.class);

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {
        // TODO document why this method is empty
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            saveScreenshot();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestResult iTestResult) {
    }

    public void onFinish(ITestResult iTestResult) {
    }

    private void saveScreenshot() throws MalformedURLException, URISyntaxException {
        File screenshotCapture = ((TakesScreenshot) DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            String screenshotFilePath = ".//src/test/test_output/screenshots/"
                    + getCurrentTimeAsString()
                    + ".png";
            File screenshotFile = new File(screenshotFilePath);
            FileUtils.copyFile(screenshotCapture, screenshotFile);
            String rpMessage = "test message for Report Portal";
            ReportPortal.emitLog(rpMessage, "DEBUG", Calendar.getInstance().getTime(), screenshotCapture);


            log.debug("Screenshot of failed test was created and saved");

           // LOGGER.info("RP_MESSAGE#FILE#{}#{}", screenshotFile.getAbsolutePath(), "I'm logging content via temp file");


        } catch (IOException e) {
            log.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}

