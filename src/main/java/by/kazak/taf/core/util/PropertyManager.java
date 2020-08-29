package by.kazak.taf.core.util;

import java.util.ResourceBundle;

public class PropertyManager {

    private static final ResourceBundle generalProperty, qaProperty, webProperty;

    static {
        generalProperty = ResourceBundle.getBundle("config.generalConfig");
        qaProperty = ResourceBundle.getBundle("config.qa");
        webProperty = ResourceBundle.getBundle("config.web");
    }

    enum Env {
        QA, WEB
    }

    public static String getProperty(String property) {
        return generalProperty.getString(property);
    }

    public static String getProperty(String property, String environment) {
        Env env = Env.valueOf(environment.toUpperCase());
        // TODO: add possibility to choose env from System.property
        return switch (env) {
            case QA -> qaProperty.getString(property);
            case WEB -> webProperty.getString(property);
        };
    }
}
