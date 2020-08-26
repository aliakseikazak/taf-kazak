package by.kazak.taf.core.config;

import by.kazak.taf.core.util.PropertyManager;

public interface ConfigData {

    String APP_NAME = PropertyManager.getProperty("app.name");
    
    String BROWSER = PropertyManager.getProperty("app.config.browser");
    int WAIT_TIMEOUT_SECONDS = Integer.parseInt(PropertyManager.getProperty("selenium.wait.page.load"));

    String BASE_URL = PropertyManager.getProperty("app.base.page");
    String LOGIN_URL = PropertyManager.getProperty("app.login.page");

    String SUPER_USER_NAME = PropertyManager.getProperty("app.users.superuser.name");
    String SUPER_USER_PASSWORD = PropertyManager.getProperty("app.users.superuser.password");
}
