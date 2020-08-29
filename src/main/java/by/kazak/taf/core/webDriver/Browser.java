package by.kazak.taf.core.webDriver;

import java.util.concurrent.TimeUnit;
import javax.naming.NamingException;

import org.openqa.selenium.WebDriver;

import by.kazak.taf.core.config.ConfigData;
import lombok.extern.log4j.Log4j2;

@Log4j2
public final class Browser {

    private static Browser instance = new Browser();
    private static final ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(Browser::initDriver);

    private Browser() {
        log.info("Init browser");
    }

    public static Browser getInstance() {
        if (instance == null) {
            synchronized (Browser.class) {
                if (instance == null) {
                    instance = new Browser();
                }
            }
        }
        return instance;
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(initDriver());
        }
        return driver.get();
    }

    public void closeDriver() {
        try {
            log.info(String.format("Closing '%s' browser...", ConfigData.BROWSER));
            getDriver().quit();
            log.info(String.format("-====Browser '%s' closed====-", ConfigData.BROWSER));
        } catch (Exception e) {
            log.error(this, e);
        } finally {
            if (isBrowserAlive()) {
                driver.set(null);
            }
        }
    }

    private boolean isBrowserAlive() {
        return driver.get() != null;
    }

    public void openBaseUrl() {
        windowMaximise();
        navigate(ConfigData.BASE_URL);
    }

    public void windowMaximise() {
        getDriver().manage().window().maximize();
        log.info("-====Browser maximized====-");
    }

    public void navigate(final String url) {
        getDriver().navigate().to(url);
    }

    private static WebDriver initDriver() {
        try {
            WebDriver driver = BrowserFactory.setBrowser();
            driver.manage().timeouts().implicitlyWait(ConfigData.BROWSER_TIMEOUT, TimeUnit.SECONDS);
            log.info(String.format("-====Browser '%s' opened====-", ConfigData.BROWSER));
            return driver;
        } catch (NamingException e) {
            log.error(e);
            e.printStackTrace();
        }
        return null;
    }
}
