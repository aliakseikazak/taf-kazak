package by.kazak.taf.business.page.dashboard;

import by.kazak.taf.business.model.Dashboard;
import by.kazak.taf.business.page.Page;
import lombok.extern.log4j.Log4j2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

@Log4j2
public class DashboardPage extends AllDashboardsPage {

    private static final String DASHBOARD_ADDED_SUCCESSFULLY = "Dashboard has been added";

    private static String allDashboardsLinkXPath = "//a[text()='All Dashboards']";
    private static String dashboardNameXPath = "//span[@title]";

    public DashboardPage() {
        super(Page.DASHBOARD);
    }

    public AllDashboardsPage clickAllDashboardLink() {
        click(allDashboardsLinkXPath);
        return new AllDashboardsPage();
    }

    public DashboardPage validateDashboardName(Dashboard dashboard) {
        assertThat(String.format("Check that '%s' dashboard name appears correctly", dashboard.getName()),
                getText(dashboardNameXPath), is(equalToIgnoringCase(dashboard.getName())));
        return this;
    }

    protected DashboardPage validateDashboardAddedMessage() {
        validateAppInfoMessages(DASHBOARD_ADDED_SUCCESSFULLY);
        return this;
    }
}
