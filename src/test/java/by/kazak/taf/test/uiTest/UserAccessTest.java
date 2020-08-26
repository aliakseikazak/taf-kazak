package by.kazak.taf.test.uiTest;

import org.testng.annotations.Test;

import by.kazak.taf.business.common.BaseTest;
import by.kazak.taf.business.page.LoginPage;
import by.kazak.taf.business.page.MainPage;
import by.kazak.taf.test.testData.model.User;
import by.kazak.taf.test.testData.service.UserCreator;

public class UserAccessTest extends BaseTest {

    @Test
    public void validUserCanLogin() {
        User superUser = UserCreator.getSuperUser();

        /* @Step 1: Log in as Super user; Expected: Main Page appears */
        MainPage mainPage = new LoginPage(driver).openPage().login(superUser);

        /* @Step 2: Validate logged in user name; Expected: Logged in user name match super user name */
        mainPage.validateLoggedInUserName(superUser.getUserName());
    }
}
