package by.kazak.taf.business.page.dashboard;

import by.kazak.taf.business.model.Dashboard;
import by.kazak.taf.business.model.User;
import by.kazak.taf.business.page.BasePage;
import by.kazak.taf.business.page.Page;
import by.kazak.taf.business.page.common.element.ButtonElement;
import by.kazak.taf.business.page.settings.SettingsPage;
import lombok.extern.log4j.Log4j2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

@Log4j2
public class AllDashboardsPage extends BasePage {

    private static final String DASHBOARD_DELETED_SUCCESSFULLY = "Dashboard has been deleted";

    private static String settingsBtnXPath = "//a[contains(@class, 'appHeader__settings-btn')]";
    private static String loggedInUserXPath = "//div[contains(@class, 'username')]";
    private static String specificDashboardXPathFormat = "//a[contains(@class, 'dashboardTable__name')][text()='%s']/..";
    private static String iconXPathFormat = "//div[./i[contains(@class, '%s')]]";

    public AllDashboardsPage(Page page) {
        super(page);
    }

    public AllDashboardsPage() {
        super(Page.ALL_DASHBOARDS);
    }


    enum Icon {
        EDIT("pencil"), DELETE("delete");

        private final String iconName;

        Icon(String iconName) {
            this.iconName = iconName;
        }
    }

    private String getSpecificDashboardRootXpath(Dashboard dashboard) {
        return String.format(specificDashboardXPathFormat, dashboard.getName());
    }

    public SettingsPage openSettingsPage() {
        click(settingsBtnXPath);
        return new SettingsPage();
    }

    public AddEditDashboardModelPage clickAddNewDashboard() {
        clickSpecificBtn(ButtonElement.ADD_NEW_DASHBOARD);
        return new AddEditDashboardModelPage();
    }

    public AllDashboardsPage deleteDashboard(Dashboard dashboard) {
        clickDeleteDashboard(dashboard).clickDeleteBtn();
        validateDashboardDeletedMessage();
        return this;
    }

    public DeleteDashboardModelPage clickDeleteDashboard(Dashboard dashboard) {
        click(String.format(BASE_FORMAT, getSpecificDashboardRootXpath(dashboard), String.format(iconXPathFormat, Icon.DELETE.iconName)));
        return new DeleteDashboardModelPage();
    }

    public AddEditDashboardModelPage clickEditDashboard(Dashboard dashboard) {
        click(String.format(BASE_FORMAT, getSpecificDashboardRootXpath(dashboard), String.format(iconXPathFormat, Icon.EDIT.iconName)));
        return new AddEditDashboardModelPage();
    }

    public AllDashboardsPage validateLoggedInUserLogin(User user) {
        assertThat(String.format("Check that '%s' username appears correctly", user.getUserLogin()),
                getText(loggedInUserXPath), is(equalToIgnoringCase(user.getUserLogin())));
        return this;
    }

    public AllDashboardsPage validateDashboardExist(Dashboard dashboard) {
        assertThat(String.format("Check that '%s' dashboard exist", dashboard.getName()),
                isElementVisible(getSpecificDashboardRootXpath(dashboard)), is(true));
        return this;
    }

    public AllDashboardsPage validateDashboardNotExist(Dashboard dashboard) {
        assertThat(String.format("Check that '%s' dashboard not exist", dashboard.getName()),
                isElementInvisible(getSpecificDashboardRootXpath(dashboard)), is(true));
        return this;
    }

    protected AllDashboardsPage validateDashboardDeletedMessage() {
        validateAppInfoMessages(DASHBOARD_DELETED_SUCCESSFULLY);
        return this;
    }
}
