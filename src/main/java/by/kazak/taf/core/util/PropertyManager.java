package by.kazak.taf.core.util;

import java.util.ResourceBundle;

public class PropertyManager {

    private static final ResourceBundle generalProperty, qaProperty, webProperty;

    static {
        generalProperty = ResourceBundle.getBundle("config.generalConfig");
        qaProperty = ResourceBundle.getBundle("config.qa");
        webProperty = ResourceBundle.getBundle("config.web");
    }

    public static String getProperty(String property) {
        return generalProperty.getString(property);
    }

    public static String getProperty(String property, String environment) {
        // TODO: add possibility to choose env from System.property
        return switch (environment) {
            case "qa" -> qaProperty.getString(property);
            case "web" -> webProperty.getString(property);
            default -> throw new IllegalArgumentException(String.format("Environment '%s' type is not supported!", environment));
        };
    }
}
