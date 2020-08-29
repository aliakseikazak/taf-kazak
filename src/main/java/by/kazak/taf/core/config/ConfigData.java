package by.kazak.taf.core.config;

import by.kazak.taf.core.util.PropertyManager;

public class ConfigData {

    public static final String APP_NAME = PropertyManager.getProperty("app.name");
    public static final String APP_ENV = PropertyManager.getProperty("app.env");

    public static String BROWSER = PropertyManager.getProperty("app.config.browser");
    public static final String BROWSER_VERSION = PropertyManager.getProperty(String.format("selenium.%s.version", BROWSER));
    public static final long BROWSER_TIMEOUT = Long.parseLong(PropertyManager.getProperty("selenium.browser.timeout"));
    public static final int WAIT_TIMEOUT_SECONDS = Integer.parseInt(PropertyManager.getProperty("selenium.wait.page.load"));
    public static final int POLLING_INTERVAL = Integer.parseInt(PropertyManager.getProperty("selenium.wait.polling.interval"));

    public static final String BASE_URL = PropertyManager.getProperty("app.base.page.url", APP_ENV);
    public static final String BASE_API_URL = String.format(PropertyManager.getProperty("app.api.base.url"), BASE_URL);

    public static final String SUPER_USER_NAME = PropertyManager.getProperty("app.users.superuser.name", APP_ENV);
    public static final String SUPER_USER_PASSWORD = PropertyManager.getProperty("app.users.superuser.password", APP_ENV);
    public static final String DEFAULT_USER_NAME = PropertyManager.getProperty("app.users.default.name", APP_ENV);
    public static final String DEFAULT_USER_PASSWORD = PropertyManager.getProperty("app.users.default.password", APP_ENV);
}
