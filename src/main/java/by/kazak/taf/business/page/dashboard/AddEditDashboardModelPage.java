package by.kazak.taf.business.page.dashboard;

import by.kazak.taf.business.model.Dashboard;
import by.kazak.taf.business.page.common.ModalWindowPage;
import by.kazak.taf.business.page.common.element.ButtonElement;
import by.kazak.taf.business.page.common.element.InputElement;

public class AddEditDashboardModelPage extends ModalWindowPage {

    public static String shareSliderXPath = "//label[contains(@class, 'inputBigSwitcher')]";
    public static String shareSliderTurnOnXPath = shareSliderXPath + "//*[contains(@class, 'turned-on')]";

    public DashboardPage addNewDashboard(Dashboard dashboard) {
        addEditAllDashboardFields(dashboard);
        return clickAddBtn();
    }

    public AllDashboardsPage editDashboard(Dashboard dashboard) {
        addEditAllDashboardFields(dashboard);
        return clickUpdateBtn();
    }

    private void addEditAllDashboardFields(Dashboard dashboard) {
        setDashboardName(dashboard);
        setDashboardDescription(dashboard);
        turnOnOffShareDashboard(dashboard);
    }

    public AddEditDashboardModelPage setDashboardName(Dashboard dashboard) {
        sendKeysToSpecificInput(dashboard.getName(), InputElement.ENTER_DASHBOARD_NAME);
        return this;
    }

    public AddEditDashboardModelPage setDashboardDescription(Dashboard dashboard) {
        sendKeysToSpecificInput(dashboard.getDescription(), InputElement.ENTER_DASHBOARD_DESCRIPTION);
        return this;
    }

    public AddEditDashboardModelPage turnOnOffShareDashboard(Dashboard dashboard) {
        if (dashboard.isShare()) {
            if (isElementVisible(shareSliderTurnOnXPath)) return this;
            click(shareSliderXPath);
            return this;
        }
        if (isElementInvisible(shareSliderTurnOnXPath)) return this;
        click(shareSliderXPath);
        return this;
    }

    public DashboardPage clickAddBtn() {
        clickSpecificBtn(ButtonElement.ADD);
        return new DashboardPage().validateDashboardAddedMessage();
    }

    public AllDashboardsPage clickUpdateBtn() {
        clickSpecificBtn(ButtonElement.UPDATE);
        return new AllDashboardsPage();
    }
}
