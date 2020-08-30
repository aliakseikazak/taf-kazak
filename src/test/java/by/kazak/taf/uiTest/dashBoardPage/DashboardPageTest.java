package by.kazak.taf.uiTest.dashBoardPage;

import org.testng.annotations.Test;

import by.kazak.taf.business.common.BaseTest;
import by.kazak.taf.business.dataProvider.CommonDataProvider;
import by.kazak.taf.business.model.User;
import by.kazak.taf.business.page.DashboardPage;
import by.kazak.taf.business.page.LoginPage;
import by.kazak.taf.business.service.UserCreatorService;
import by.kazak.taf.core.util.TestGroup;

public class DashboardPageTest extends BaseTest {

    /**
     * @author Aliaksei Kazak
     */
    @Test(groups = {TestGroup.UI, TestGroup.REGRESSION}, description = "Verifies user can add dashboard")
    public void userCanAddDashboard() {
        User user = UserCreatorService.getDefaultUser();

        /* @Step 1: Log in; Expected: Dashboard page appears */
        DashboardPage dashboardPage = new LoginPage().login(user);

        /* @Step 2: Validate logged in user name; Expected: Logged in user name match super user name */
        System.out.println();
    }
}
