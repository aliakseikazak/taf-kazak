package by.kazak.taf.core.webDriver;

import by.kazak.taf.core.utilities.PropertyManager;

enum Browser {
    CHROME(PropertyManager.getProperty("selenium.chromedriver.version")),
    FIREFOX(PropertyManager.getProperty("selenium.geckodriver.version"));

    private final String version;

    Browser(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
