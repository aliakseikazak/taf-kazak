package by.kazak.taf.business.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import by.kazak.taf.core.config.ConfigData;

public abstract class AbstractPage implements ConfigData {

    protected WebDriver driver;
    protected Logger log = LogManager.getRootLogger();

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    protected abstract AbstractPage openPage();
}
