package by.kazak.taf.business.page;

import by.kazak.taf.business.page.settings.SettingsPage;
import lombok.extern.log4j.Log4j2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

@Log4j2
public class DashboardPage extends BasePage {

    private static String settingsBtnXPath = "//a[contains(@class, 'appHeader__settings-btn')]";
    private static String loggedInUserXPath = "//div[contains(@class, 'username')]";

    public DashboardPage() {
        super(Page.DASHBOARD);
    }

    public SettingsPage openSettingsPage() {
        click(settingsBtnXPath);
        return new SettingsPage();
    }

    public DashboardPage validateLoggedInUserName(String userName) {
        assertThat(String.format("Check that '%s' username appears correctly", userName),
                getText(loggedInUserXPath), is(equalToIgnoringCase(userName)));
        return this;
    }
}
