package by.kazak.taf.business.page;

import org.openqa.selenium.WebDriver;

import by.kazak.taf.core.webDriver.Browser;

public abstract class AbstractPage {

    protected WebDriver driver;

    protected AbstractPage() {
        this.driver = Browser.getDriver();
    }
}
