package by.kazak.taf.core.webDriver;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import by.kazak.taf.core.config.ConfigData;

public class WebDriverDecorator implements WebDriver, ConfigData {

    protected WebDriver driver;
    private static final Logger LOG = LogManager.getRootLogger();

    public WebDriverDecorator(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void get(String url) {
        driver.get(url);
        LOG.info(String.format("Go to '%s' url", url));
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        LOG.info(String.format("Closing '%s' browser...", BROWSER));
        driver.quit();
        LOG.info(String.format("-====Browser '%s' closed====-", BROWSER));
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }
}
