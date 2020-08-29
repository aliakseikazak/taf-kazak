package by.kazak.taf.core.webDriver;

import java.util.EnumMap;
import javax.naming.NamingException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import by.kazak.taf.core.config.ConfigData;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

public final class BrowserFactory {

    private BrowserFactory() {
        throw new IllegalStateException("Utility class");
    }

    public enum BrowserType {
        CHROME {
            @Override
            public ChromeDriver getDriver() {
                return new ChromeDriver();
            }

            @Override
            public DriverManagerType getDriverType() {
                return DriverManagerType.CHROME;
            }
        },
        FIREFOX {
            @Override
            public FirefoxDriver getDriver() {
                return new FirefoxDriver();
            }

            @Override
            public DriverManagerType getDriverType() {
                return DriverManagerType.FIREFOX;
            }
        };

        public abstract WebDriver getDriver();

        public abstract DriverManagerType getDriverType();
    }
    
    static WebDriver setBrowser() throws NamingException {
        BrowserType browserType = BrowserType.valueOf(ConfigData.BROWSER.toUpperCase());
        WebDriver driver;
        switch (browserType) {
            case CHROME, FIREFOX -> {
                WebDriverManager.getInstance(browserType.getDriverType()).browserVersion(ConfigData.BROWSER_VERSION).setup();
                driver = browserType.getDriver();
            }
            default -> throw new NamingException(String.format("Wrong Browser Name: %s", ConfigData.BROWSER));
        }
        return driver;
    }
}
