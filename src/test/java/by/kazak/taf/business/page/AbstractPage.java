package by.kazak.taf.business.page;

import org.openqa.selenium.WebDriver;

import by.kazak.taf.core.config.ConfigData;

public abstract class AbstractPage implements ConfigData {

    protected WebDriver driver;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    protected abstract AbstractPage openPage();
}
