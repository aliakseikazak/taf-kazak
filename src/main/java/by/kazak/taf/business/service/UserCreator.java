package by.kazak.taf.business.service;

import by.kazak.taf.business.model.User;
import by.kazak.taf.business.model.UserApi;
import by.kazak.taf.core.config.ConfigData;
import by.kazak.taf.core.util.MockDataUtils;

public class UserCreator {

    public static User getSuperUser() {
        return new User(ConfigData.SUPER_USER_NAME, ConfigData.SUPER_USER_PASSWORD, ConfigData.SUPER_USER_TOKEN);
    }

    public static User getDefaultUser() {
        return new User(ConfigData.DEFAULT_USER_NAME, ConfigData.DEFAULT_USER_PASSWORD, ConfigData.DEFAULT_USER_TOKEN);
    }

    public static User generateNewUser() {
        return new User(MockDataUtils.generateName(), MockDataUtils.generatePassword());
    }

    public static UserApi generateNewUserForApi(String projectName) {
        return new UserApi("USER", projectName, MockDataUtils.generateEmail(), MockDataUtils.generateFullName(),
                MockDataUtils.generateName(), MockDataUtils.generatePassword(), "CUSTOMER");
    }
}
