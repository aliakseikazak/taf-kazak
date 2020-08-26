package by.kazak.taf.business.common;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import by.kazak.taf.core.config.ConfigData;
import by.kazak.taf.core.webDriver.DriverSingleton;

public class BaseTest implements ConfigData {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
