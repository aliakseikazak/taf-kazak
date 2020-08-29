package by.kazak.taf.business.service;

import by.kazak.taf.business.model.User;
import by.kazak.taf.core.config.ConfigData;
import by.kazak.taf.core.util.MockDataUtils;

public class UserCreator {

    public static User getSuperUser() {
        return new User(ConfigData.SUPER_USER_NAME, ConfigData.SUPER_USER_PASSWORD);
    }

    public static User getDefaultUser() {
        return new User(ConfigData.DEFAULT_USER_NAME, ConfigData.DEFAULT_USER_PASSWORD);
    }

    public static User generateNewUser() {
        return new User(MockDataUtils.generateUserName(), MockDataUtils.generatePassword());
    }
}
