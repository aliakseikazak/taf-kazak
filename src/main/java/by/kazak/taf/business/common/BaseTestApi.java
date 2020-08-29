package by.kazak.taf.business.common;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import by.kazak.taf.core.config.ConfigData;
import io.restassured.RestAssured;

public class BaseTestApi extends Base {

    @BeforeSuite
    @Override
    public void beforeSuite() {
        ConfigData.BROWSER = "none";
        super.beforeSuite();
    }

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = ConfigData.BASE_API_URL;
    }
}
