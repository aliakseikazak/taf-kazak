package by.kazak.taf.business.service;

import java.net.URI;
import java.util.Objects;

import by.kazak.taf.business.model.User;
import by.kazak.taf.business.common.HTTPMethod;
import by.kazak.taf.business.common.BaseApiAssertion;
import by.kazak.taf.business.apiResources.userController.UserAPIPath;
import by.kazak.taf.business.apiResources.userController.UserURIPath;
import by.kazak.taf.core.util.TestResource;
import io.restassured.response.ValidatableResponse;

public class UserApiService extends RestApiService implements BaseApiAssertion {

    private static final URI GET_RESPONSES = Objects.requireNonNull(TestResource.getResourceAsFile(UserURIPath.GET.getUriPath())).toURI();

    public ValidatableResponse getUserInfo(User user) {
        ValidatableResponse response = HTTPMethod.get(user, setApiPath(UserAPIPath.USER_INFO, user.getUserLogin()));
        GETChecks(response, GET_RESPONSES);
        return response;
    }
}
