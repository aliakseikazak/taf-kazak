package by.kazak.taf.apiTest;

import org.testng.annotations.Test;

import by.kazak.taf.business.common.BaseTestApi;
import by.kazak.taf.business.restApi.service.UserApiService;
import by.kazak.taf.business.service.UserCreator;

public class AppHealthCheckTest extends BaseTestApi {

    @Test(description = "GET superuser info")
    public void getPlaylistByNonexistentSlug () {
        String superUserLogin = UserCreator.getSuperUser().getUserLogin();
        
        /* @Step 1: Check that superuser exist; Expected: Valid response */
        UserApiService userApiService = new UserApiService();
        userApiService.getUserInfo(superUserLogin);
    }
}
