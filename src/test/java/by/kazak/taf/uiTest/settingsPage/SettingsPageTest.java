package by.kazak.taf.uiTest.settingsPage;

import org.testng.annotations.Test;

import by.kazak.taf.business.common.BaseTest;
import by.kazak.taf.business.model.User;
import by.kazak.taf.business.page.DashboardPage;
import by.kazak.taf.business.page.LoginPage;
import by.kazak.taf.business.page.settings.GeneralSettingsPage;
import by.kazak.taf.business.page.settings.SettingsPage;
import by.kazak.taf.business.page.settings.SettingsTab;
import by.kazak.taf.business.service.UserCreatorService;
import by.kazak.taf.core.util.TestGroup;

public class SettingsPageTest extends BaseTest {

    /**
     * @author Aliaksei Kazak
     */
    @Test(groups = {TestGroup.UI, TestGroup.REGRESSION}, description = "Verifies user is able to change general settings")
    public void userCanChangeLaunchInactivityTimeoutSettings() {
        User user = UserCreatorService.getDefaultUser();

        /* @Step 1: Log in as default user; Expected: Dashboard page appears */
        DashboardPage dashboardPage = new LoginPage().login(user);

        /* @Step 2: Open settings; Expected: Setting page appears */
        SettingsPage settingsPage = dashboardPage.openSettingsPage();

        /* @Step 3: Open 'general' settings tab; Expected: General setting tab appears */
        GeneralSettingsPage generalSettingsPage = settingsPage.openTab(SettingsTab.GENERAL, GeneralSettingsPage.class);

        /* @Step 4: Select '1 day' from 'Launch inactivity timeout' dropdown; Expected: Selected appears in expected dropdown */
        generalSettingsPage.selectValueFromLaunchInactivityTimeoutDropdown(GeneralSettingsPage.DropdownValue.ONE_DAY);
        generalSettingsPage.validateLaunchInactivityTimeoutDropdownValue(GeneralSettingsPage.DropdownValue.ONE_DAY);

        /* @Step 5: Save 'general' settings; Expected: Settings are saved and selected appears in expected dropdown */
        generalSettingsPage.saveSettings();
        generalSettingsPage.validateLaunchInactivityTimeoutDropdownValue(GeneralSettingsPage.DropdownValue.ONE_DAY);
    }
}
