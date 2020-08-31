package by.kazak.taf.business.common;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import by.kazak.taf.core.config.ConfigData;
import by.kazak.taf.core.util.TestListener;

@Listeners({TestListener.class})
public class BaseTestApi extends Base {

    @BeforeSuite
    @Override
    public void beforeSuite() {
        ConfigData.BROWSER = "none";
        super.beforeSuite();
    }
}
