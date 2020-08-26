package by.kazak.taf.test.testData.service;

import by.kazak.taf.core.config.ConfigData;
import by.kazak.taf.test.testData.model.User;

public class UserCreator implements ConfigData {

    public static User getSuperUser() {
        return new User(SUPER_USER_NAME, SUPER_USER_PASSWORD);
    }
}