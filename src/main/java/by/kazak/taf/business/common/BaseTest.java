package by.kazak.taf.business.common;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import by.kazak.taf.core.webDriver.Browser;

public class BaseTest extends Base {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Browser.getInstance().openBaseUrl();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Browser.getInstance().closeDriver();
    }

    @Override
    protected void initParams() {
    }
}
