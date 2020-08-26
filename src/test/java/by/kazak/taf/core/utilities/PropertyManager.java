package by.kazak.taf.core.utilities;

import java.util.ResourceBundle;

public class PropertyManager {

    private static final ResourceBundle generalProperty;

    static {
        generalProperty = ResourceBundle.getBundle("test-config.generalConfig");
    }

    public static String getProperty(String property) {
        return generalProperty.getString(property);
    }
}
