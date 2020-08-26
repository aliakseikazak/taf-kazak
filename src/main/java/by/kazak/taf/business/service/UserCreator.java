package by.kazak.taf.business.service;

import by.kazak.taf.core.config.ConfigData;
import by.kazak.taf.core.util.MockDataUtils;
import by.kazak.taf.business.model.User;

public class UserCreator implements ConfigData {

    public static User getSuperUser() {
        return new User(SUPER_USER_NAME, SUPER_USER_PASSWORD);
    }

    public static User generateNewUser() {
        return new User(MockDataUtils.generateUserName(), MockDataUtils.generatePassword());
    }
}
