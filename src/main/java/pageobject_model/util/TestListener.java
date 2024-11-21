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

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class TestListener implements ITestListener {

    private final Logger log = LogManager.getRootLogger();
    private static final Logger LOGGER = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        try {
            saveScreenshotToReportPortal("Screenshot of test start to RP", "DEBUG");
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void onTestSuccess(ITestResult iTestResult) {
        // TODO document why this method is empty
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            saveScreenshotToFile();
            saveScreenshotToReportPortal("Screenshot of failed test to RP", "INFO");
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

    private void saveScreenshotToReportPortal(String rpMessage, String logLevel) throws MalformedURLException, URISyntaxException {

        File screenshotCapture = ((TakesScreenshot) DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        ReportPortal.emitLog(rpMessage, logLevel, Calendar.getInstance().getTime(), screenshotCapture);
        log.debug("Screenshot of failed test was created and saved");
    }

    private void saveScreenshotToFile() throws MalformedURLException, URISyntaxException {
        File screenshotCapture = ((TakesScreenshot) DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        String filePath = ".//src/test/test_output/screenshots/"
                + getCurrentTimeAsString()
                + ".png";
        try (Closeable closable = () -> FileUtils.copyFile(screenshotCapture, new File(filePath));) {
            FileUtils.copyFile(screenshotCapture, new File(filePath));
            log.debug("Screenshot of failed test was created and saved");
        } catch (IOException e) {
            log.error(String.format("Failed to save screenshot: %s", e.getLocalizedMessage()));
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}

