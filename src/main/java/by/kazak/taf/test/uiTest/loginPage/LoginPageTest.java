package by.kazak.taf.test.uiTest.loginPage;

import org.testng.annotations.Test;

import by.kazak.taf.business.common.BaseTest;
import by.kazak.taf.business.dataProvider.CommonDataProvider;
import by.kazak.taf.business.model.User;
import by.kazak.taf.business.page.LoginPage;
import by.kazak.taf.business.page.dashboard.AllDashboardsPage;
import by.kazak.taf.core.util.TestGroup;

public class LoginPageTest extends BaseTest {

    /**
     * @author Aliaksei Kazak
     */
    @Test(dataProvider = CommonDataProvider.USER_ROLES_DATA_PROVIDER, dataProviderClass = CommonDataProvider.class,
            groups = {TestGroup.UI, TestGroup.REGRESSION}, description = "Verifies regular user and admin user are able to sign-in")
    public void validUserCanLogin(User user) {

        /* @Step 1: Log in as Super user; Expected: 'All Dashboard' page appears */
        AllDashboardsPage allDashboardsPage = new LoginPage().login(user);

        /* @Step 2: Validate logged in user name; Expected: Logged in user name match super user name */
        allDashboardsPage.validateLoggedInUserLogin(user);
    }
}
