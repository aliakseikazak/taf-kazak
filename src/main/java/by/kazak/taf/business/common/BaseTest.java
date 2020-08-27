package by.kazak.taf.business.common;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import by.kazak.taf.core.config.ConfigData;
import by.kazak.taf.core.util.TestListener;
import by.kazak.taf.core.webDriver.DriverSingleton;

@Listeners({TestListener.class})
public class BaseTest implements ConfigData {

    protected WebDriver driver;
    private final Logger log = LogManager.getRootLogger();

    @BeforeSuite
    public void beforeSuite() {
        environmentLogger();
    }

    @BeforeMethod
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }

    private void environmentLogger() {
        // TODO: add more useful info
        log.info("====ENVIRONMENT INFO====");
        Map<Object, Object> envInfo = Stream.of(new String[][]{
                {"APP", APP_NAME},
                {"ENVIRONMENT", APP_ENV},
                {"BROWSER", BROWSER}
        }).collect(Collectors.toMap(key -> key[0], value -> value[1]));
        envInfo.forEach((key, value) -> log.info(String.format("[%s] = %s", key, value)));
        log.info("========================");
    }
}
