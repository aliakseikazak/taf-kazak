package by.kazak.taf.core.webDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import by.kazak.taf.core.config.ConfigData;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSingleton implements ConfigData {

    private static WebDriver driver;
    private static final Logger LOG = LogManager.getRootLogger();

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (null == driver) {
            // TODO: add possibility to choose browser from System.property
            switch (BROWSER) {
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().browserVersion(Browser.FIREFOX.getVersion()).setup();
                    driver = getFirefoxDriver();
                }
                default -> {
                    WebDriverManager.chromedriver().browserVersion(Browser.CHROME.getVersion()).setup();
                    driver = getChromeDriver();
                }
            }
            LOG.info(String.format("-====Browser '%s' opened====-", BROWSER));
            driver.manage().window().maximize();
            LOG.info("-====Browser maximized====-");
        }
        return driver;
    }

    private static WebDriverDecorator getFirefoxDriver() {
        return new WebDriverDecorator(new FirefoxDriver());
    }

    private static WebDriverDecorator getChromeDriver() {
        return new WebDriverDecorator(new ChromeDriver());
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
