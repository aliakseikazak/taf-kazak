package by.kazak.taf.business.restApi.service;

import java.net.URI;
import java.util.Objects;

import by.kazak.taf.business.restApi.HTTPMethod;
import by.kazak.taf.business.restApi.assertion.BaseAssertion;
import by.kazak.taf.business.restApi.resources.UserAPIPath;
import by.kazak.taf.business.restApi.resources.UserURIPath;
import by.kazak.taf.core.util.TestResource;
import io.restassured.response.ValidatableResponse;

public class UserApiService extends RestApiService implements BaseAssertion {

    private static final URI GET_RESPONSES = Objects.requireNonNull(TestResource.getResourceAsFile(UserURIPath.GET.getUriPath())).toURI();

    public ValidatableResponse getUserInfo(String userLogin) {
        ValidatableResponse response = HTTPMethod.get(setApiPath(UserAPIPath.USER_INFO, userLogin));
        GETChecks(response, GET_RESPONSES);
        return response;
    }
}
