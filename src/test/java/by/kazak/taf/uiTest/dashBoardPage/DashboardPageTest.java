package by.kazak.taf.uiTest.dashBoardPage;

import org.testng.annotations.Test;

import by.kazak.taf.business.common.BaseTest;
import by.kazak.taf.business.model.Dashboard;
import by.kazak.taf.business.model.User;
import by.kazak.taf.business.page.LoginPage;
import by.kazak.taf.business.page.dashboard.AddEditDashboardModelPage;
import by.kazak.taf.business.page.dashboard.AllDashboardsPage;
import by.kazak.taf.business.page.dashboard.DashboardPage;
import by.kazak.taf.business.service.DashboardCreator;
import by.kazak.taf.core.util.MockDataUtils;
import by.kazak.taf.core.util.TestGroup;
import by.kazak.taf.setup.TestDataSetup;

public class DashboardPageTest extends BaseTest {

    /**
     * @author Aliaksei Kazak
     */
    @Test(groups = {TestGroup.UI, TestGroup.REGRESSION}, description = "Verifies user can add dashboard")
    public void userCanAddDashboard() {
        User user = TestDataSetup.getNewUser();
        Dashboard dashboard = DashboardCreator.generateNewDashboard(false);

        /* @Step 1: Log in; Expected: 'ALL DASHBOARDS' page appears */
        AllDashboardsPage allDashboardsPage = new LoginPage().login(user);

        /* @Step 2: Open 'ADD NEW DASHBOARD' model page; Expected: 'ADD NEW DASHBOARD' model page appears */
        AddEditDashboardModelPage addEditDashboardModelPage = allDashboardsPage.clickAddNewDashboard();

        /* @Step 3: Add new dashboard with random data; Expected: Dashboard added successfully */
        DashboardPage dashboardPage = addEditDashboardModelPage.addNewDashboard(dashboard);

        /* @Step 4: Check that dashboard name correct; Expected: Dashboard name as expected */
        dashboardPage.validateDashboardName(dashboard);

        /* @Step 5: Return to 'ALL DASHBOARDS' page; Expected: 'ALL DASHBOARDS' page appears */
        dashboardPage.clickAllDashboardLink();

        /* @Step 6: Check that dashboard added successfully; Expected: Dashboard is shown in the table */
        allDashboardsPage.validateDashboardExist(dashboard);
    }

    /**
     * @author Aliaksei Kazak
     */
    @Test(groups = {TestGroup.UI, TestGroup.REGRESSION}, description = "Verifies user can delete dashboard")
    public void userCanDeleteDashboard() {
        User user = TestDataSetup.getNewUser();
        Dashboard dashboard = DashboardCreator.generateNewDashboard();

        /* @Step 1: Log in; Expected: 'ALL DASHBOARDS' page appears */
        AllDashboardsPage allDashboardsPage = new LoginPage().login(user);

        /* @Step 2: Open 'ADD NEW DASHBOARD' model page; Expected: 'ADD NEW DASHBOARD' model page appears */
        AddEditDashboardModelPage addEditDashboardModelPage = allDashboardsPage.clickAddNewDashboard();

        /* @Step 3: Add new dashboard with random data; Expected: Dashboard added successfully */
        DashboardPage dashboardPage = addEditDashboardModelPage.addNewDashboard(dashboard);

        /* @Step 4: Return to 'ALL DASHBOARDS' page; Expected: 'ALL DASHBOARDS' page appears */
        dashboardPage.clickAllDashboardLink();

        /* @Step 5: Delete created dashboard; Expected: Dashboard deleted, info message appears */
        allDashboardsPage.deleteDashboard(dashboard);

        /* @Step 6: Check that dashboard deleted successfully; Expected: Dashboard is not shown in the table */
        allDashboardsPage.validateDashboardNotExist(dashboard);
    }

    /**
     * @author Aliaksei Kazak
     */
    @Test(groups = {TestGroup.UI, TestGroup.REGRESSION}, description = "Verifies user can edit dashboard")
    public void userCanEditDashboard() {
        User user = TestDataSetup.getNewUser();
        Dashboard dashboard = DashboardCreator.generateNewDashboard();

        /* @Step 1: Log in; Expected: 'ALL DASHBOARDS' page appears */
        AllDashboardsPage allDashboardsPage = new LoginPage().login(user);

        /* @Step 2: Open 'ADD NEW DASHBOARD' model page; Expected: 'ADD NEW DASHBOARD' model page appears */
        AddEditDashboardModelPage addEditDashboardModelPage = allDashboardsPage.clickAddNewDashboard();

        /* @Step 3: Add new dashboard with random data; Expected: Dashboard added successfully */
        DashboardPage dashboardPage = addEditDashboardModelPage.addNewDashboard(dashboard);

        /* @Step 4: Return to 'ALL DASHBOARDS' page; Expected: 'ALL DASHBOARDS' page appears */
        dashboardPage.clickAllDashboardLink();

        /* @Step 5: Open 'EDIT DASHBOARD' model page; Expected: 'EDIT DASHBOARD' model page appears */
        allDashboardsPage.clickEditDashboard(dashboard);

        /* @Step 6: Edit created dashboard; Expected: Dashboard edited, info message appears */
        dashboard.setName(MockDataUtils.generateName());
        addEditDashboardModelPage.editDashboard(dashboard);

        /* @Step 7: Check that dashboard updated successfully; Expected: Updated dashboard is shown in the table */
        allDashboardsPage.validateDashboardExist(dashboard);
    }
}
