package by.kazak.taf.business.common;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import by.kazak.taf.core.util.TestListener;
import by.kazak.taf.core.webDriver.Browser;

@Listeners({TestListener.class})
public class BaseTest extends Base {

    @BeforeMethod
    public void setUp() {
        Browser.getInstance().openBaseUrl();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Browser.getInstance().closeDriver();
    }
}
