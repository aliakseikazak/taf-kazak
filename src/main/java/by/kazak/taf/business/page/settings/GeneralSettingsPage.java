package by.kazak.taf.business.page.settings;

import by.kazak.taf.business.page.Page;
import lombok.extern.log4j.Log4j2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

@Log4j2
public class GeneralSettingsPage extends SettingsPage {

    private static final String SETTINGS_UPDATED_SUCCESSFULLY = "Project settings were successfully updated";

    private static String dropdownXPathFormat = "//div[contains(@class, 'formField')][./span[text()='%s']]";
    private static String dropdownValueXPathFormat = dropdownXPathFormat + DROPDOWN_VALUE_XPATH;

    public GeneralSettingsPage() {
        super(Page.GENERAL_SETTINGS);
    }

    enum Dropdown {
        NAME("Name"), LAUNCH_INACTIVITY_TIMEOUT("Launch inactivity timeout"), KEEP_LAUNCHES("Keep launches"),
        KEEP_LOGS("Keep logs"), KEEP_ATTACHMENTS("Keep attachments");

        private final String name;

        Dropdown(String name) {
            this.name = name;
        }
    }

    public enum DropdownValue {
        ONE_HOUR("1 hour"), THREE_HOURS("3 hours"), SIX_HOURS("6 hours"), TWELVE_HOURS("12 hours"), ONE_DAY("1 day"), SEVEN_DAYS("7 days"),
        FOURTEEN_DAYS("14 days"), TWENTY_ONE_DAYS("21 days"), TREATY_DAYS("30 days"), NINETY_DAYS("90 days"), HALF_YEAR("180 days"), FOREVER("Forever");

        private final String value;

        DropdownValue(String value) {
            this.value = value;
        }
    }

    public GeneralSettingsPage selectValueFromLaunchInactivityTimeoutDropdown(DropdownValue value) {
        return selectValueFromDropdownList(Dropdown.LAUNCH_INACTIVITY_TIMEOUT, value);
    }

    private GeneralSettingsPage selectValueFromDropdownList(Dropdown dropdown, DropdownValue value) {
        selectValueFromDropdown(value.value, String.format(dropdownXPathFormat, dropdown.name));
        return this;
    }

    public GeneralSettingsPage validateLaunchInactivityTimeoutDropdownValue(DropdownValue value) {
        return validateDropdownValue(Dropdown.LAUNCH_INACTIVITY_TIMEOUT, value);
    }

    private GeneralSettingsPage validateDropdownValue(Dropdown dropdown, DropdownValue value) {
        assertThat("Check that dropdown value appears as expected",
                getText(String.format(dropdownValueXPathFormat, dropdown.name)), is(equalToIgnoringCase(value.value)));
        return this;
    }

    public GeneralSettingsPage saveSettings() {
        clickSubmitBtn();
        validateSettingsUpdatedSuccessfulMessage();
        return this;
    }

    private void validateSettingsUpdatedSuccessfulMessage() {
        validateAppInfoMessages(SETTINGS_UPDATED_SUCCESSFULLY);
    }
}
