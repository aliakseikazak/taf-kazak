package by.kazak.taf.business.page;

public enum Page {
    LOGIN("login-page-content"), DASHBOARD("dashboard-page-header"), SETTINGS("settings-page"), GENERAL_SETTINGS("general-tab");

    private String pageInitXPath;

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
