package by.kazak.taf.business.page;

public enum Page {
    LOGIN("login-page-content"), ALL_DASHBOARDS("dashboard-page"), DASHBOARD("dashboard-item"), SETTINGS("settings-page"),
    GENERAL_SETTINGS("general-tab"), MODAL_WINDOW("modal-window");

    private final String pageInitXPath;

    Page(String pageInitXPath) {
        this.pageInitXPath = pageInitXPath;
    }

    public String getPageInitXPath() {
        return pageInitXPath;
    }

    @Override
    public String toString() {
        return String.format("'%s' page is opened", name());
    }
}
