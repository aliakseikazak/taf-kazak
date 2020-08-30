package by.kazak.taf.business.service;

import java.net.URI;

import by.kazak.taf.business.common.BaseApiAssertion;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.HTTP_OK;

public class RestApiService implements BaseApiAssertion {

    protected String setApiPath(Enum apiPath, String controller) {
        return String.format(apiPath.toString(), controller);
    }

    private void generalChecks(ValidatableResponse response, int statusCode, URI uri) {
        checkStatusCode(response, statusCode);
        checkContentType(response, ContentType.JSON);
        checkMatchJsonSchema(response, uri);
    }

    protected void GETGeneralChecks(ValidatableResponse response, URI uri) {
        generalChecks(response, HTTP_OK, uri);
    }
}
