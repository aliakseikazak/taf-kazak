package by.kazak.taf.business.page.settings;

public enum SettingsTab {
    GENERAL("General"), INTEGRATIONS("Integrations"), NOTIFICATIONS("Notifications"), DEFECT_TYPES("Defect types"),
    AUTO_ANALYSIS("Auto-Analysis"), PATTERN_ANALYSIS("Pattern-analysis"), DEMO_DATA("Demo data");

    private final String tabName;

    SettingsTab(String tabName) {
        this.tabName = tabName;
    }

    public String getTabName() {
        return tabName;
    }

    @Override
    public String toString() {
        return String.format("//a[contains(@class, 'navigationTabs__tab')][./*[text()='%s'] | text()='%s']", getTabName(), getTabName());
    }
}
