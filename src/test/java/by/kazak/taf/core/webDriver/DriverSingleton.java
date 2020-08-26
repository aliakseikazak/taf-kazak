package by.kazak.taf.core.webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import by.kazak.taf.core.config.ConfigData;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSingleton implements ConfigData {

    private static WebDriver driver;

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (null == driver) {
            switch (BROWSER) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().browserVersion(Browser.FIREFOX.getVersion()).setup();
                    driver = getFirefoxDriver();
                }
                default: {
                    WebDriverManager.chromedriver().browserVersion(Browser.CHROME.getVersion()).setup();
                    driver = getChromeDriver();
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    private static FirefoxDriver getFirefoxDriver() {
        return new FirefoxDriver();
    }

    private static ChromeDriver getChromeDriver() {
        return new ChromeDriver();
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
