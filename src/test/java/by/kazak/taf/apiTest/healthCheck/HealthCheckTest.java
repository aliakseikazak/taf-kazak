package by.kazak.taf.apiTest.healthCheck;

import org.testng.annotations.Test;

import by.kazak.taf.business.common.BaseTestApi;
import by.kazak.taf.business.model.User;
import by.kazak.taf.business.service.UserApiService;
import by.kazak.taf.business.service.UserCreatorService;
import by.kazak.taf.core.util.TestGroup;
import io.restassured.response.ValidatableResponse;

public class HealthCheckTest extends BaseTestApi {

    @Test(groups = {TestGroup.REST_API, TestGroup.HEALTH_CHECK}, description = "Verifies portal is up and running. Get superuser info")
    public void superuserExist() {
        User superUser = UserCreatorService.getSuperUser();

        /* @Step 1: Check that superuser exist; Expected: Valid response */
        UserApiService userApiService = new UserApiService();
        ValidatableResponse response = userApiService.getUserInfo(superUser);
        
        /* @Step 2: Check that in response correct user; Expected: User correct */
        userApiService.validateUserLogin(response, superUser);
    }
}
