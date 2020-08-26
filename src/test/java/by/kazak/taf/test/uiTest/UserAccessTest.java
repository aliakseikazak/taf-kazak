package by.kazak.taf.test.uiTest;

import org.testng.annotations.Test;

import by.kazak.taf.business.common.BaseTest;
import by.kazak.taf.business.page.LoginPage;
import by.kazak.taf.business.page.MainPage;

public class UserAccessTest extends BaseTest {

    @Test
    public void validUserCanLogin() {
        String superUserName = SUPER_USER_NAME;

        /* @Step 1: Log in as Super user; Expected: Main Page appears */
        MainPage mainPage = new LoginPage(driver).openPage().login(superUserName, SUPER_USER_PASSWORD);

        /* @Step 2: Validate logged in user name; Expected: Logged in user name match super user name */
        mainPage.validateLoggedInUserName(superUserName);
    }
}
