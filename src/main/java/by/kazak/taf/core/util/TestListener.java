package by.kazak.taf.core.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import by.kazak.taf.core.webDriver.DriverSingleton;

public class TestListener implements ITestListener {

    private static final Logger LOG = LogManager.getRootLogger();

    @Override
    public void onTestStart(ITestResult result) {
        // TODO: add info about test
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO: tell that test passed
    }

    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshot();
    }

    private void saveScreenshot() {
        File screenCapture = ((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(String.format(".//target//screenshots//%s.png", DateUtils.getCurrentDateTimeAsString())));
        } catch (IOException e) {
            LOG.error(String.format("Failed to save screenshot: %s", e.getLocalizedMessage()));
        }
    }
}
