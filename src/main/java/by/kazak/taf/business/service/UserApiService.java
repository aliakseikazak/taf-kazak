package by.kazak.taf.business.service;

import java.net.URI;
import java.util.Objects;

import by.kazak.taf.business.apiResources.userController.UserAPIPath;
import by.kazak.taf.business.apiResources.userController.UserFieldPath;
import by.kazak.taf.business.apiResources.userController.UserURIPath;
import by.kazak.taf.business.common.BaseApiAssertion;
import by.kazak.taf.business.common.HTTPMethod;
import by.kazak.taf.business.model.User;
import by.kazak.taf.business.model.UserApi;
import by.kazak.taf.core.util.TestResource;
import io.restassured.response.ValidatableResponse;
import lombok.extern.log4j.Log4j2;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Log4j2
public class UserApiService extends RestApiService implements BaseApiAssertion {

    private static final URI GET_RESPONSES = Objects.requireNonNull(TestResource.getResourceAsFile(UserURIPath.GET.getUriPath())).toURI();

    public ValidatableResponse getUserInfo(User user) {
        ValidatableResponse response = HTTPMethod.get(user, setApiPath(UserAPIPath.USER_LOGIN, user.getUserLogin()));
        response.log().ifError();
        GETGeneralChecks(response, GET_RESPONSES);
        return response;
    }

    public ValidatableResponse postCreateUser(User user, UserApi newUser) {
        ValidatableResponse response = HTTPMethod.post(user, setApiPath(UserAPIPath.USER), newUser);
        response.log().ifError();
        return response;
    }

    public void validateUserLogin(ValidatableResponse response, User user) {
        String userLogin = user.getUserLogin();
        assertThat(String.format("Check that user login is equal to: '%s'", userLogin),
                getUserId(response, UserFieldPath.USER_ID), equalTo(userLogin));
        log.info("Valid user login in response, got: '{}'", userLogin);
    }
}
