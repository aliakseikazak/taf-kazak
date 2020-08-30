package by.kazak.taf.business.dataProvider;

import org.testng.annotations.DataProvider;

import by.kazak.taf.business.service.UserCreatorService;

public class CommonDataProvider {

    public static final String USER_ROLES_DATA_PROVIDER = "userRolesDataProvider";

    @DataProvider(name = USER_ROLES_DATA_PROVIDER, parallel = true)
    private static Object[] userRolesDataProvider() {
        return new Object[]{
                UserCreatorService.getSuperUser(),
                UserCreatorService.getDefaultUser()
        };
    }
}
