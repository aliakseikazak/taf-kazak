package by.kazak.taf.core.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import by.kazak.taf.core.webDriver.Browser;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        log.info(String.format("\n-=========================================[ TEST %s.%s STARTED ]==========================================-", 
                result.getTestClass().getName(), result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(String.format("\n-==========================================[ TEST %s.%s PASSED ]==========================================-", 
                result.getTestClass().getName(), result.getMethod().getMethodName()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error(String.format("\n-=========================================[ TEST %s.%s FAILED ]=========================================-", 
                result.getTestClass().getName(), result.getMethod().getMethodName()));
        saveScreenshot(result);
    }

    private void saveScreenshot(ITestResult result) {
        File file = ((TakesScreenshot) Browser.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(String.valueOf(
                    Paths.get("target", "screenshots", String.format("%s.png", DateUtils.getCurrentDateTimeAsString())))));
        } catch (IOException e) {
            log.error(String.format("Failed to save screenshot: %s", e.getLocalizedMessage()));
            e.printStackTrace();
        }
        log.error(result.getThrowable());
    }
}
