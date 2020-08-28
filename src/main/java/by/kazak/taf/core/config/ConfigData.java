package by.kazak.taf.core.config;

import by.kazak.taf.core.util.PropertyManager;

public interface ConfigData {

    String APP_NAME = PropertyManager.getProperty("app.name");
    String APP_ENV = PropertyManager.getProperty("app.env");
    
    String BROWSER = PropertyManager.getProperty("app.config.browser");
    int WAIT_TIMEOUT_SECONDS = Integer.parseInt(PropertyManager.getProperty("selenium.wait.page.load"));
    int POLLING_INTERVAL = Integer.parseInt(PropertyManager.getProperty("selenium.wait.polling.interval"));

    String BASE_URL = PropertyManager.getProperty("app.base.page", APP_ENV);
    String LOGIN_URL = String.format(PropertyManager.getProperty("app.login.page"), BASE_URL);

    String SUPER_USER_NAME = PropertyManager.getProperty("app.users.superuser.name", APP_ENV);
    String SUPER_USER_PASSWORD = PropertyManager.getProperty("app.users.superuser.password", APP_ENV);
}
